import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PersistenceTest {
    private val console = ConsoleCoarseGrainedObject()

    @Test
    fun `dependentObject changed for persistence test`() {
        val dependentObject = MessageDependentObject()
        console.init()
        console.dependentObjects[0] = dependentObject
        val message = "Danger"
        assertNull(console.dependentObjects[0].data)
        dependentObject.data = message
        assertEquals(message, console.dependentObjects[0].data)
    }

    @Test
    fun `coarse grained object changed for persistence test`() {
        val dependentObject = MessageDependentObject()
        console.init()
        console.dependentObjects[0] = dependentObject
        val message = "Danger"
        assertNull(console.dependentObjects[0].data)
        console.setData(message)
        assertEquals(message, dependentObject.data)
    }
}