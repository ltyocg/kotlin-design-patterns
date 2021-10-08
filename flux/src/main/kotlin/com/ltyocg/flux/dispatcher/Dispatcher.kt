package com.ltyocg.flux.dispatcher

import com.ltyocg.flux.action.*
import com.ltyocg.flux.store.Store
import java.util.*

object Dispatcher {
    private val stores = LinkedList<Store>()
    fun registerStore(store: Store) {
        stores.add(store)
    }

    fun menuItemSelected(menuItem: MenuItem) {
        dispatchAction(MenuAction(menuItem))
        dispatchAction(ContentAction(if (menuItem == MenuItem.COMPANY) Content.COMPANY else Content.PRODUCTS))
    }

    private fun dispatchAction(action: Action) {
        stores.forEach { it.onAction(action) }
    }
}