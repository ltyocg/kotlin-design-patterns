import action.*
import java.util.*

abstract class Store {
    private val views = LinkedList<View>()
    abstract fun onAction(action: Action)
    fun registerView(view: View) {
        views.add(view)
    }

    fun notifyChange() = views.forEach { it.storeChanged(this) }
}

class ContentStore : Store() {
    var content = Content.PRODUCTS
    override fun onAction(action: Action) {
        if (action.type == ActionType.CONTENT_CHANGED) {
            content = (action as ContentAction).content
            notifyChange()
        }
    }
}

class MenuStore : Store() {
    var selected = MenuItem.HOME
    override fun onAction(action: Action) {
        if (action.type == ActionType.MENU_ITEM_SELECTED) {
            selected = (action as MenuAction).menuItem
            notifyChange()
        }
    }
}