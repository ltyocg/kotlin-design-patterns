import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class StewTest {
    @Test
    fun mix() {
        val stew = ImmutableStew(1, 2, 3, 4)
        val assertListAppender = assertListAppender(ImmutableStew::class)
        repeat(20) {
            assertListAppender.clear()
            stew.mix()
            assertEquals("Mixing the immutable stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers", assertListAppender.lastMessage())
        }
    }
}