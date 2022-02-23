package store

import action.Action
import action.ActionType
import action.MenuAction
import action.MenuItem

class MenuStore : Store() {
    var selected = MenuItem.HOME
    override fun onAction(action: Action) {
        if (action.type == ActionType.MENU_ITEM_SELECTED) {
            selected = (action as MenuAction).menuItem
            notifyChange()
        }
    }
}