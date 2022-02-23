package view

import action.Content
import org.slf4j.LoggerFactory
import store.ContentStore
import store.Store

class ContentView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    private var content = Content.PRODUCTS
    override fun storeChanged(store: Store) {
        content = (store as ContentStore).content
        render()
    }

    override fun render() {
        log.info(content.toString())
    }
}