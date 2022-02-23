package store

import action.Action
import action.ActionType
import action.Content
import action.ContentAction

class ContentStore : Store() {
    var content = Content.PRODUCTS
    override fun onAction(action: Action) {
        if (action.type == ActionType.CONTENT_CHANGED) {
            content = (action as ContentAction).content
            notifyChange()
        }
    }
}