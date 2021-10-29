package com.ltyocg.leaderelection

interface MessageManager {
    fun sendHeartbeatMessage(leaderId: Int): Boolean
    fun sendElectionMessage(currentId: Int, content: String): Boolean
    fun sendLeaderMessage(currentId: Int, leaderId: Int): Boolean
    fun sendHeartbeatInvokeMessage(currentId: Int)
}

abstract class AbstractMessageManager(protected val instanceMap: Map<Int, Instance>) : MessageManager {
    protected fun findNextInstance(currentId: Int): Instance = instanceMap.entries.asSequence()
        .filter { (k, v) -> k > currentId && v.alive }
        .sortedBy { it.key }
        .firstOrNull()?.value ?: instanceMap.entries.asSequence()
        .filter { it.value.alive }
        .sortedBy { it.key }
        .firstOrNull()?.value!!
}

class BullyMessageManager(instanceMap: Map<Int, Instance>) : AbstractMessageManager(instanceMap) {
    override fun sendHeartbeatMessage(leaderId: Int): Boolean = instanceMap[leaderId]!!.alive
    override fun sendElectionMessage(currentId: Int, content: String): Boolean {
        val candidateList = instanceMap.entries.filter { (k, v) -> k < currentId && v.alive }
        return if (candidateList.isEmpty()) true
        else {
            val electionMessage = Message(MessageType.ELECTION_INVOKE, "")
            candidateList.forEach { it.value.onMessage(electionMessage) }
            false
        }
    }

    override fun sendLeaderMessage(currentId: Int, leaderId: Int): Boolean {
        val leaderMessage = Message(MessageType.LEADER, leaderId.toString())
        instanceMap.entries.asSequence()
            .filter { it.key != currentId }
            .forEach { it.value.onMessage(leaderMessage) }
        return false
    }

    override fun sendHeartbeatInvokeMessage(currentId: Int) {
        findNextInstance(currentId).onMessage(Message(MessageType.HEARTBEAT_INVOKE, ""))
    }
}

class RingMessageManager(instanceMap: Map<Int, Instance>) : AbstractMessageManager(instanceMap) {
    override fun sendHeartbeatMessage(leaderId: Int): Boolean = instanceMap[leaderId]!!.alive
    override fun sendElectionMessage(currentId: Int, content: String): Boolean {
        findNextInstance(currentId).onMessage(Message(MessageType.ELECTION, content))
        return true
    }

    override fun sendLeaderMessage(currentId: Int, leaderId: Int): Boolean {
        findNextInstance(currentId).onMessage(Message(MessageType.LEADER, leaderId.toString()))
        return true
    }

    override fun sendHeartbeatInvokeMessage(currentId: Int) {
        findNextInstance(currentId).onMessage(Message(MessageType.HEARTBEAT_INVOKE, ""))
    }
}