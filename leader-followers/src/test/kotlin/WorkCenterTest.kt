import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class WorkCenterTest {
    @Test
    fun `test create workers`() {
        val workCenter = WorkCenter()
        workCenter.createWorkers(5, TaskSet(), TaskHandler())
        assertEquals(5, workCenter.workers.size)
        assertEquals(workCenter.workers[0], workCenter.leader)
    }

    @Test
    fun `test null leader`() {
        assertNull(WorkCenter().apply { promoteLeader() }.leader)
    }

    @Test
    fun `test promote leader`() {
        val workCenter = WorkCenter()
        workCenter.createWorkers(5, TaskSet(), TaskHandler())
        workCenter.removeWorker(workCenter.leader!!)
        workCenter.promoteLeader()
        assertEquals(4, workCenter.workers.size)
        assertEquals(workCenter.workers[0], workCenter.leader)
    }
}