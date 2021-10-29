package com.ltyocg.leaderelection

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BullyMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
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
            launch { instance1() }
            launch { instance2() }
            launch { instance3() }
            launch { instance4() }
            launch { instance5() }
            instance1.alive = false
        }
    }
}

class RingMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
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
            launch { instance1() }
            launch { instance2() }
            launch { instance3() }
            launch { instance4() }
            launch { instance5() }
            instance1.alive = false
        }
    }
}