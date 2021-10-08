package com.ltyocg.flux.view

import com.ltyocg.flux.action.Content
import com.ltyocg.flux.store.ContentStore
import com.ltyocg.flux.store.Store
import org.slf4j.LoggerFactory

class ContentView : View {
    private val log = LoggerFactory.getLogger(this::class.java)
    private var content = Content.PRODUCTS
    override fun storeChanged(store: Store) {
        content = (store as ContentStore).content
        render()
    }

    override fun render() {
        log.info(content.toString())
    }
}