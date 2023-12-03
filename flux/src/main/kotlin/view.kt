import action.Content
import action.MenuItem
import io.github.oshai.kotlinlogging.KotlinLogging

interface View {
    fun storeChanged(store: Store)
    fun render()
}

class ContentView : View {
    private val logger = KotlinLogging.logger {}
    private var content = Content.PRODUCTS
    override fun storeChanged(store: Store) {
        content = (store as ContentStore).content
        render()
    }

    override fun render() = logger.info { content }
}

class MenuView : View {
    private val logger = KotlinLogging.logger {}
    private var selected = MenuItem.HOME
    override fun storeChanged(store: Store) {
        selected = (store as MenuStore).selected
    }

    override fun render() = MenuItem.entries.forEach {
        logger.info { "${if (selected == it) "* " else ""}$it" }
    }

    fun itemClicked(item: MenuItem) = Dispatcher.menuItemSelected(item)
}
