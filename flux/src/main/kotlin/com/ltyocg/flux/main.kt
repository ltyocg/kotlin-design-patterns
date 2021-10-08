package com.ltyocg.flux

import com.ltyocg.flux.action.MenuItem
import com.ltyocg.flux.dispatcher.Dispatcher
import com.ltyocg.flux.store.ContentStore
import com.ltyocg.flux.store.MenuStore
import com.ltyocg.flux.view.ContentView
import com.ltyocg.flux.view.MenuView

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