import kotlin.test.Test
import kotlin.test.assertEquals

class TaskSetTest {
    @Test
    fun `test add task`() {
        val taskSet = TaskSet().apply { addTask(Task(10)) }
        assertEquals(1, taskSet.size)
    }

    @Test
    fun `test get task`() {
        val taskSet = TaskSet().apply { addTask(Task(100)) }
        val task = taskSet.getTask()
        assertEquals(100, task.time)
        assertEquals(0, taskSet.size)
    }
}