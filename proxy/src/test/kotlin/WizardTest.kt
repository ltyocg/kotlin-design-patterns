import kotlin.test.Test
import kotlin.test.assertEquals

class WizardTest {
    @Test
    fun testToString() = listOf("Gandalf", "Dumbledore", "Oz", "Merlin")
        .forEach { assertEquals(it, Wizard(it).toString()) }
}