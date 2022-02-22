import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import kotlin.test.Test

class EventDispatcherTest {
    @Test
    fun `test event driver pattern`() {
        val dispatcher = spy(EventDispatcher())
        val userCreatedEventHandler = spy(UserCreatedEventHandler())
        val userUpdatedEventHandler = spy(UserUpdatedEventHandler())
        dispatcher.registerHandler(userCreatedEventHandler)
        dispatcher.registerHandler(userUpdatedEventHandler)
        val user = User("ltyocg")
        val userCreatedEvent = UserCreatedEvent(user)
        val userUpdatedEvent = UserUpdatedEvent(user)
        dispatcher.dispatch(userCreatedEvent)
        verify(userCreatedEventHandler).onEvent(userCreatedEvent)
        verify(dispatcher).dispatch(userCreatedEvent)
        dispatcher.dispatch(userUpdatedEvent)
        verify(userUpdatedEventHandler).onEvent(userUpdatedEvent)
        verify(dispatcher).dispatch(userUpdatedEvent)
    }
}