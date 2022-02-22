import java.util.*

class Wizard {
    private val undoStack = LinkedList<() -> Unit>()
    private val redoStack = LinkedList<() -> Unit>()
    fun castSpell(runnable: () -> Unit) {
        runnable()
        undoStack.offerLast(runnable)
    }

    fun undoLastSpell() {
        if (undoStack.isNotEmpty()) {
            val previousSpell = undoStack.pollLast()
            redoStack.offerLast(previousSpell)
            previousSpell()
        }
    }

    fun redoLastSpell() {
        if (redoStack.isNotEmpty()) {
            val previousSpell = redoStack.pollLast()
            undoStack.offerLast(previousSpell)
            previousSpell()
        }
    }

    override fun toString(): String = "Wizard"
}