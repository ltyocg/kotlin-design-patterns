import action.MenuItem

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