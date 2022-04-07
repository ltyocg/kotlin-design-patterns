import kotlin.concurrent.thread

object BullyMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceMap = mutableMapOf<Int, Instance>()
        val messageManager = BullyMessageManager(instanceMap)
        val instance1 = BullyInstance(messageManager, 1, 1)
        val instance2 = BullyInstance(messageManager, 2, 1)
        val instance3 = BullyInstance(messageManager, 3, 1)
        val instance4 = BullyInstance(messageManager, 4, 1).apply { onMessage(Message(MessageType.HEARTBEAT_INVOKE, "")) }
        val instance5 = BullyInstance(messageManager, 5, 1)
        instanceMap.apply {
            put(1, instance1)
            put(2, instance2)
            put(3, instance3)
            put(4, instance4)
            put(5, instance5)
        }
        thread(block = instance1)
        thread(block = instance2)
        thread(block = instance3)
        thread(block = instance4)
        thread(block = instance5)
        instance1.alive = false
    }
}

object RingMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceMap = mutableMapOf<Int, Instance>()
        val messageManager = RingMessageManager(instanceMap)
        val instance1 = RingInstance(messageManager, 1, 1)
        val instance2 = RingInstance(messageManager, 2, 1).apply { onMessage(Message(MessageType.HEARTBEAT_INVOKE, "")) }
        val instance3 = RingInstance(messageManager, 3, 1)
        val instance4 = RingInstance(messageManager, 4, 1)
        val instance5 = RingInstance(messageManager, 5, 1)
        instanceMap.apply {
            put(1, instance1)
            put(2, instance2)
            put(3, instance3)
            put(4, instance4)
            put(5, instance5)
        }
        thread(block = instance1)
        thread(block = instance2)
        thread(block = instance3)
        thread(block = instance4)
        thread(block = instance5)
        instance1.alive = false
    }
}