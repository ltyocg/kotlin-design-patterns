package com.ltyocg.command

import kotlin.test.Test
import kotlin.test.assertEquals

class CommandTest {
    @Test
    fun testCommand() {
        val wizard = Wizard()
        val goblin = Goblin()
        wizard.castSpell(goblin::changeSize)
        goblin.verify(Size.SMALL, Visibility.VISIBLE)
        wizard.castSpell(goblin::changeVisibility)
        goblin.verify(Size.SMALL, Visibility.INVISIBLE)
        wizard.undoLastSpell()
        goblin.verify(Size.SMALL, Visibility.VISIBLE)
        wizard.undoLastSpell()
        goblin.verify(Size.NORMAL, Visibility.VISIBLE)
        wizard.redoLastSpell()
        goblin.verify(Size.SMALL, Visibility.VISIBLE)
        wizard.redoLastSpell()
        goblin.verify(Size.SMALL, Visibility.INVISIBLE)
    }

    private fun Goblin.verify(expectedSize: Size, expectedVisibility: Visibility) {
        assertEquals("Goblin", toString(), "Goblin's name must be same as expectedName")
        assertEquals(expectedSize, size, "Goblin's size must be same as expectedSize")
        assertEquals(expectedVisibility, visibility, "Goblin's visibility must be same as expectedVisibility")
    }
}