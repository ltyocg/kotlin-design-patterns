import kotlin.concurrent.thread
import kotlin.test.Test

class TaskGenSrvExeTest {
    @Test
    fun test() {
        val msgQueue = MessageQueue()
        thread(block = TaskGenerator(msgQueue, 1))
        thread(block = ServiceExecutor(msgQueue))
    }
}