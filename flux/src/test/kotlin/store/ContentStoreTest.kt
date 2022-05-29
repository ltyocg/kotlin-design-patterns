package store

import ContentStore
import action.Content
import action.ContentAction
import action.MenuAction
import action.MenuItem
import org.mockito.kotlin.*
import view.View
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentStoreTest {
    @Test
    fun onAction() {
        val contentStore = ContentStore()
        val view = mock<View>()
        contentStore.registerView(view)
        verifyNoInteractions(view)
        contentStore.onAction(MenuAction(MenuItem.PRODUCTS))
        verifyNoInteractions(view)
        contentStore.onAction(ContentAction(Content.COMPANY))
        verify(view, times(1)).storeChanged(eq(contentStore))
        verifyNoMoreInteractions(view)
        assertEquals(Content.COMPANY, contentStore.content)
    }
}