package com.ltyocg.flux.view

import com.ltyocg.flux.store.Store

interface View {
    fun storeChanged(store: Store)
    fun render()
}