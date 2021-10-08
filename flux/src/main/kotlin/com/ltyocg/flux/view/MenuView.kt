package com.ltyocg.flux.view

import com.ltyocg.flux.action.MenuItem
import com.ltyocg.flux.dispatcher.Dispatcher
import com.ltyocg.flux.store.MenuStore
import com.ltyocg.flux.store.Store
import org.slf4j.LoggerFactory

class MenuView : View {
    private val log = LoggerFactory.getLogger(this::class.java)
    private var selected = MenuItem.HOME
    override fun storeChanged(store: Store) {
        selected = (store as MenuStore).selected
    }

    override fun render() {
        MenuItem.values().forEach {
            if (selected == it) log.info("* {}", it)
            else log.info(it.toString())
        }
    }

    fun itemClicked(item: MenuItem) {
        Dispatcher.menuItemSelected(item)
    }
}