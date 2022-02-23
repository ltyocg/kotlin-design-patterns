package view

import store.Store

interface View {
    fun storeChanged(store: Store)
    fun render()
}