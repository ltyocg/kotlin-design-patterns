package store

import action.Action
import view.View
import java.util.*

abstract class Store {
    private val views = LinkedList<View>()
    abstract fun onAction(action: Action)
    fun registerView(view: View) {
        views.add(view)
    }

    fun notifyChange() {
        views.forEach { it.storeChanged(this) }
    }
}