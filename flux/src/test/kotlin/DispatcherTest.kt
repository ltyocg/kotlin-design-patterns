import action.*
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class DispatcherTest {
    @Test
    fun menuItemSelected() {
        val store = mock<Store>()
        Dispatcher.registerStore(store)
        Dispatcher.menuItemSelected(MenuItem.HOME)
        Dispatcher.menuItemSelected(MenuItem.COMPANY)
        val actionCaptor = argumentCaptor<Action>()
        verify(store, times(4)).onAction(actionCaptor.capture())
        verifyNoMoreInteractions(store)
        val actions = actionCaptor.allValues
        val menuActions = actions.asSequence()
            .filter { it.type == ActionType.MENU_ITEM_SELECTED }
            .map { it as MenuAction }
            .toList()
        assertEquals(2, menuActions.size)
        assertEquals(1, menuActions.asSequence().map(MenuAction::menuItem).count { it == MenuItem.HOME })
        assertEquals(1, menuActions.asSequence().map(MenuAction::menuItem).count { it == MenuItem.COMPANY })
        val contentActions = actions.asSequence()
            .filter { it.type == ActionType.CONTENT_CHANGED }
            .map { it as ContentAction }
            .toList()
        assertEquals(2, contentActions.size)
        assertEquals(1, contentActions.asSequence().map(ContentAction::content).count { it == Content.PRODUCTS })
        assertEquals(1, contentActions.asSequence().map(ContentAction::content).count { it == Content.COMPANY })
    }
}