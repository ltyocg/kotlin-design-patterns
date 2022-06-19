import kotlin.test.Test
import kotlin.test.assertEquals

class CommandTest {
    @Test
    fun command() {
        Wizard.castSpell(Goblin::changeSize)
        Goblin.verify(Size.SMALL, Visibility.VISIBLE)
        Wizard.castSpell(Goblin::changeVisibility)
        Goblin.verify(Size.SMALL, Visibility.INVISIBLE)
        Wizard.undoLastSpell()
        Goblin.verify(Size.SMALL, Visibility.VISIBLE)
        Wizard.undoLastSpell()
        Goblin.verify(Size.NORMAL, Visibility.VISIBLE)
        Wizard.redoLastSpell()
        Goblin.verify(Size.SMALL, Visibility.VISIBLE)
        Wizard.redoLastSpell()
        Goblin.verify(Size.SMALL, Visibility.INVISIBLE)
    }

    private fun Goblin.verify(expectedSize: Size, expectedVisibility: Visibility) {
        assertEquals("Goblin", toString(), "Goblin's name must be same as expectedName")
        assertEquals(expectedSize, size, "Goblin's size must be same as expectedSize")
        assertEquals(expectedVisibility, visibility, "Goblin's visibility must be same as expectedVisibility")
    }
}