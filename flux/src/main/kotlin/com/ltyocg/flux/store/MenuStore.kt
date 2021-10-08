package com.ltyocg.flux.store

import com.ltyocg.flux.action.Action
import com.ltyocg.flux.action.ActionType
import com.ltyocg.flux.action.MenuAction
import com.ltyocg.flux.action.MenuItem

class MenuStore : Store() {
    var selected = MenuItem.HOME
    override fun onAction(action: Action) {
        if (action.type == ActionType.MENU_ITEM_SELECTED) {
            selected = (action as MenuAction).menuItem
            notifyChange()
        }
    }
}