package com.ltyocg.flux.store

import com.ltyocg.flux.action.Action
import com.ltyocg.flux.action.ActionType
import com.ltyocg.flux.action.Content
import com.ltyocg.flux.action.ContentAction

class ContentStore : Store() {
    var content = Content.PRODUCTS
    override fun onAction(action: Action) {
        if (action.type == ActionType.CONTENT_CHANGED) {
            content = (action as ContentAction).content
            notifyChange()
        }
    }
}