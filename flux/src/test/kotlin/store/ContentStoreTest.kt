package store

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
    fun `test onAction`() {
        val contentStore = ContentStore()
        val view = mock<View>()
        contentStore.registerView(view)
        verifyZeroInteractions(view)
        contentStore.onAction(MenuAction(MenuItem.PRODUCTS))
        verifyZeroInteractions(view)
        contentStore.onAction(ContentAction(Content.COMPANY))
        verify(view, times(1)).storeChanged(eq(contentStore))
        verifyNoMoreInteractions(view)
        assertEquals(Content.COMPANY, contentStore.content)
    }
}