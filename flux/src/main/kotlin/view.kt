import action.Content
import action.MenuItem
import org.slf4j.LoggerFactory

interface View {
    fun storeChanged(store: Store)
    fun render()
}

class ContentView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    private var content = Content.PRODUCTS
    override fun storeChanged(store: Store) {
        content = (store as ContentStore).content
        render()
    }

    override fun render() = log.info(content.toString())
}

class MenuView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    private var selected = MenuItem.HOME
    override fun storeChanged(store: Store) {
        selected = (store as MenuStore).selected
    }

    override fun render() = MenuItem.values().forEach {
        if (selected == it) log.info("* {}", it)
        else log.info(it.toString())
    }

    fun itemClicked(item: MenuItem) = Dispatcher.menuItemSelected(item)
}