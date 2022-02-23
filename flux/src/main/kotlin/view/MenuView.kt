package view

import action.MenuItem
import dispatcher.Dispatcher
import org.slf4j.LoggerFactory
import store.MenuStore
import store.Store

class MenuView : View {
    private val log = LoggerFactory.getLogger(javaClass)
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