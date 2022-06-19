import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PersistenceTest {
    @Test
    fun `dependentObject changed for persistence`() {
        ConsoleCoarseGrainedObject.init()
        ConsoleCoarseGrainedObject.dependentObjects[0] = MessageDependentObject
        val message = "Danger"
        assertNull(ConsoleCoarseGrainedObject.dependentObjects[0].data)
        MessageDependentObject.data = message
        assertEquals(message, ConsoleCoarseGrainedObject.dependentObjects[0].data)
    }

    @Test
    fun `coarse grained object changed for persistence`() {
        ConsoleCoarseGrainedObject.init()
        ConsoleCoarseGrainedObject.dependentObjects[0] = MessageDependentObject
        val message = "Danger"
        assertNull(ConsoleCoarseGrainedObject.dependentObjects[0].data)
        ConsoleCoarseGrainedObject.setData(message)
        assertEquals(message, MessageDependentObject.data)
    }
}