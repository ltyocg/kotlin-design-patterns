import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class ImmutableStewTest {
    @Test
    fun mix() {
        val stew = Stew(1, 2, 3, 4)
        val assertListAppender = assertListAppender(Stew::class)
        repeat(20) {
            assertListAppender.clear()
            stew.mix()
            assertEquals("Mixing the stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers", assertListAppender.lastMessage())
        }
    }

    @Test
    fun drink() {
        val stew = Stew(1, 2, 3, 4)
        val assertListAppender = assertListAppender(Stew::class)
        stew.mix()
        assertEquals("Mixing the stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers", assertListAppender.lastMessage())
        stew.taste()
        assertEquals("Tasting the stew", assertListAppender.lastMessage())
        stew.mix()
        assertEquals("Mixing the stew we find: 0 potatoes, 1 carrots, 2 meat and 3 peppers", assertListAppender.lastMessage())
    }
}