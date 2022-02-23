package com.ltyocg.flux

import action.MenuItem
import dispatcher.Dispatcher
import store.ContentStore
import store.MenuStore
import view.ContentView
import view.MenuView

fun main() {
    val menuStore = MenuStore()
    Dispatcher.registerStore(menuStore)
    val contentStore = ContentStore()
    Dispatcher.registerStore(contentStore)
    val menuView = MenuView()
    menuStore.registerView(menuView)
    val contentView = ContentView()
    contentStore.registerView(contentView)
    menuView.render()
    contentView.render()
    menuView.itemClicked(MenuItem.COMPANY)
}