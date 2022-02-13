package com.ltyocg.leaderelection

import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentLinkedQueue

interface Instance {
    var alive: Boolean
    fun onMessage(message: Message)
}

private const val HEARTBEAT_INTERVAL = 5000L

abstract class AbstractInstance(
    protected val messageManager: MessageManager?,
    protected val localId: Int,
    protected var leaderId: Int
) : Instance, () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    private val messageQueue = ConcurrentLinkedQueue<Message>()
    override var alive: Boolean = true
    override fun invoke() {
        while (true) if (messageQueue.isNotEmpty()) {
            val message = messageQueue.remove()
            when (message.type) {
                MessageType.ELECTION -> {
                    log.info("Instance {} - Election Message handling...", localId)
                    handleElectionMessage(message)
                }
                MessageType.LEADER -> {
                    log.info("Instance {} - Leader Message handling...", localId)
                    handleLeaderMessage(message)
                }
                MessageType.HEARTBEAT -> {
                    log.info("Instance {} - Heartbeat Message handling...", localId)
                    handleHeartbeatMessage(message)
                }
                MessageType.ELECTION_INVOKE -> {
                    log.info("Instance {} - Election Invoke Message handling...", localId)
                    handleElectionInvokeMessage()
                }
                MessageType.LEADER_INVOKE -> {
                    log.info("Instance {} - Leader Invoke Message handling...", localId)
                    handleLeaderInvokeMessage()
                }
                MessageType.HEARTBEAT_INVOKE -> {
                    log.info("Instance {} - Heartbeat Invoke Message handling...", localId)
                    handleHeartbeatInvokeMessage()
                }
                else -> {}
            }
        }
    }

    override fun onMessage(message: Message) {
        messageQueue.offer(message)
    }

    protected abstract fun handleElectionMessage(message: Message)
    protected abstract fun handleElectionInvokeMessage()
    protected abstract fun handleLeaderMessage(message: Message)
    protected abstract fun handleLeaderInvokeMessage()
    protected abstract fun handleHeartbeatMessage(message: Message)
    protected abstract fun handleHeartbeatInvokeMessage()
}

class BullyInstance(messageManager: MessageManager?, localId: Int, leaderId: Int) : AbstractInstance(messageManager, localId, leaderId) {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun handleElectionMessage(message: Message) {}
    override fun handleElectionInvokeMessage() {
        if (localId != leaderId) {
            log.info("Instance {}- Start election.", localId)
            if (messageManager!!.sendElectionMessage(localId, localId.toString())) {
                log.info("Instance {}- Succeed in election. Start leader notification.", localId)
                leaderId = localId
                messageManager.sendLeaderMessage(localId, localId)
                messageManager.sendHeartbeatInvokeMessage(localId)
            }
        }
    }

    override fun handleLeaderMessage(message: Message) {
        leaderId = message.content.toInt()
        log.info("Instance {} - Leader update done.", localId)
    }

    override fun handleLeaderInvokeMessage() {}
    override fun handleHeartbeatMessage(message: Message) {}
    override fun handleHeartbeatInvokeMessage() {
        if (messageManager!!.sendHeartbeatMessage(leaderId)) {
            log.info("Instance {}- Leader is alive.", localId)
            Thread.sleep(HEARTBEAT_INTERVAL)
            messageManager.sendHeartbeatInvokeMessage(localId)
        } else {
            log.info("Instance {}- Leader is not alive. Start election.", localId)
            if (messageManager.sendElectionMessage(localId, localId.toString())) {
                log.info("Instance {}- Succeed in election. Start leader notification.", localId)
                messageManager.sendLeaderMessage(localId, localId)
            }
        }
    }
}

class RingInstance(messageManager: MessageManager?, localId: Int, leaderId: Int) : AbstractInstance(messageManager, localId, leaderId) {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun handleElectionMessage(message: Message) {
        val content = message.content
        log.info("Instance {} - Election Message: {}", localId, content)
        val candidateList = content.trim().splitToSequence(',')
            .map(String::toInt)
            .sorted()
            .toList()
        if (localId in candidateList) {
            val newLeaderId = candidateList[0]
            log.info("Instance {} - New leader should be {}.", localId, newLeaderId)
            messageManager!!.sendLeaderMessage(localId, newLeaderId)
        } else {
            messageManager!!.sendElectionMessage(localId, "$content,$localId")
        }
    }

    override fun handleElectionInvokeMessage() {}
    override fun handleLeaderMessage(message: Message) {
        val newLeaderId = message.content.toInt()
        if (leaderId != newLeaderId) {
            log.info("Instance {} - Update leaderID", localId)
            leaderId = newLeaderId
            messageManager!!.sendLeaderMessage(localId, newLeaderId)
        } else {
            log.info("Instance {} - Leader update done. Start heartbeat.", localId)
            messageManager!!.sendHeartbeatInvokeMessage(localId)
        }
    }

    override fun handleLeaderInvokeMessage() {}
    override fun handleHeartbeatMessage(message: Message) {}
    override fun handleHeartbeatInvokeMessage() {
        if (messageManager!!.sendHeartbeatMessage(leaderId)) {
            log.info("Instance {}- Leader is alive. Start next heartbeat in 5 second.", localId)
            Thread.sleep(HEARTBEAT_INTERVAL)
            messageManager.sendHeartbeatInvokeMessage(localId)
        } else {
            log.info("Instance {}- Leader is not alive. Start election.", localId)
            messageManager.sendElectionMessage(localId, localId.toString())
        }
    }
}