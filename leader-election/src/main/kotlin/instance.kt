import io.github.oshai.kotlinlogging.KotlinLogging
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
    private val logger = KotlinLogging.logger {}
    private val messageQueue = ConcurrentLinkedQueue<Message>()
    override var alive: Boolean = true
    override fun invoke() {
        while (true) if (messageQueue.isNotEmpty()) {
            val message = messageQueue.remove()
            when (message.type) {
                MessageType.ELECTION -> {
                    logger.info { "Instance $localId - Election Message handling..." }
                    handleElectionMessage(message)
                }

                MessageType.LEADER -> {
                    logger.info { "Instance $localId - Leader Message handling..." }
                    handleLeaderMessage(message)
                }

                MessageType.HEARTBEAT -> {
                    logger.info { "Instance $localId - Heartbeat Message handling..." }
                    handleHeartbeatMessage(message)
                }

                MessageType.ELECTION_INVOKE -> {
                    logger.info { "Instance $localId - Election Invoke Message handling..." }
                    handleElectionInvokeMessage()
                }

                MessageType.LEADER_INVOKE -> {
                    logger.info { "Instance $localId - Leader Invoke Message handling..." }
                    handleLeaderInvokeMessage()
                }

                MessageType.HEARTBEAT_INVOKE -> {
                    logger.info { "Instance $localId - Heartbeat Invoke Message handling..." }
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
    private val logger = KotlinLogging.logger {}
    override fun handleElectionMessage(message: Message) {}
    override fun handleElectionInvokeMessage() {
        if (localId != leaderId) {
            logger.info { "Instance $localId- Start election." }
            if (messageManager!!.sendElectionMessage(localId, localId.toString())) {
                logger.info { "Instance $localId- Succeed in election. Start leader notification." }
                leaderId = localId
                messageManager.sendLeaderMessage(localId, localId)
                messageManager.sendHeartbeatInvokeMessage(localId)
            }
        }
    }

    override fun handleLeaderMessage(message: Message) {
        leaderId = message.content.toInt()
        logger.info { "Instance $localId - Leader update done." }
    }

    override fun handleLeaderInvokeMessage() {}
    override fun handleHeartbeatMessage(message: Message) {}
    override fun handleHeartbeatInvokeMessage() {
        if (messageManager!!.sendHeartbeatMessage(leaderId)) {
            logger.info { "Instance $localId- Leader is alive." }
            Thread.sleep(HEARTBEAT_INTERVAL)
            messageManager.sendHeartbeatInvokeMessage(localId)
        } else {
            logger.info { "Instance $localId- Leader is not alive. Start election." }
            if (messageManager.sendElectionMessage(localId, localId.toString())) {
                logger.info { "Instance $localId- Succeed in election. Start leader notification." }
                messageManager.sendLeaderMessage(localId, localId)
            }
        }
    }
}

class RingInstance(messageManager: MessageManager?, localId: Int, leaderId: Int) : AbstractInstance(messageManager, localId, leaderId) {
    private val logger = KotlinLogging.logger {}
    override fun handleElectionMessage(message: Message) {
        val content = message.content
        logger.info { "Instance $localId - Election Message: $content" }
        val candidateList = content.trim().splitToSequence(',')
            .map(String::toInt)
            .sorted()
            .toList()
        if (localId in candidateList) {
            val newLeaderId = candidateList[0]
            logger.info { "Instance $localId - New leader should be $newLeaderId." }
            messageManager!!.sendLeaderMessage(localId, newLeaderId)
        } else {
            messageManager!!.sendElectionMessage(localId, "$content,$localId")
        }
    }

    override fun handleElectionInvokeMessage() {}
    override fun handleLeaderMessage(message: Message) {
        val newLeaderId = message.content.toInt()
        if (leaderId != newLeaderId) {
            logger.info { "Instance $localId - Update leaderID" }
            leaderId = newLeaderId
            messageManager!!.sendLeaderMessage(localId, newLeaderId)
        } else {
            logger.info { "Instance $localId - Leader update done. Start heartbeat." }
            messageManager!!.sendHeartbeatInvokeMessage(localId)
        }
    }

    override fun handleLeaderInvokeMessage() {}
    override fun handleHeartbeatMessage(message: Message) {}
    override fun handleHeartbeatInvokeMessage() {
        if (messageManager!!.sendHeartbeatMessage(leaderId)) {
            logger.info { "Instance $localId- Leader is alive. Start next heartbeat in 5 second." }
            Thread.sleep(HEARTBEAT_INTERVAL)
            messageManager.sendHeartbeatInvokeMessage(localId)
        } else {
            logger.info { "Instance $localId- Leader is not alive. Start election." }
            messageManager.sendElectionMessage(localId, localId.toString())
        }
    }
}
