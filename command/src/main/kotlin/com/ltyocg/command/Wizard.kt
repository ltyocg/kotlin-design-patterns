package com.ltyocg.command

import java.util.*

class Wizard {
    private val undoStack = LinkedList<Runnable>()
    private val redoStack = LinkedList<Runnable>()

    fun castSpell(runnable: Runnable) {
        runnable.run()
        undoStack.offerLast(runnable)
    }

    fun undoLastSpell() {
        if (undoStack.isNotEmpty()) {
            val previousSpell = undoStack.pollLast()
            redoStack.offerLast(previousSpell)
            previousSpell.run()
        }
    }

    fun redoLastSpell() {
        if (redoStack.isNotEmpty()) {
            val previousSpell = redoStack.pollLast()
            undoStack.offerLast(previousSpell)
            previousSpell.run()
        }
    }

    override fun toString(): String = "Wizard"
}