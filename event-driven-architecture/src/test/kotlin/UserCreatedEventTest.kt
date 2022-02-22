import kotlin.test.Test
import kotlin.test.assertEquals

class UserCreatedEventTest {
    @Test
    fun `test get event type`() {
        assertEquals(UserCreatedEvent::class, UserCreatedEvent(User("ltyocg")).type)
    }
}