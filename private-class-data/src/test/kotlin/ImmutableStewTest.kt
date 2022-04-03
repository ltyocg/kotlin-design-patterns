import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class ImmutableStewTest {
    @Test
    fun mix() {
        val stew = Stew(1, 2, 3, 4)
        repeat(20) {
            assertLogContains("Mixing the stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers") {
                stew.mix()
            }
        }
    }

    @Test
    fun testDrink() {
        val stew = Stew(1, 2, 3, 4)
        assertLogContains("Mixing the stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers") {
            stew.mix()
        }
        assertLogContains("Tasting the stew") {
            stew.taste()
        }
        assertLogContains("Mixing the stew we find: 0 potatoes, 1 carrots, 2 meat and 3 peppers") {
            stew.mix()
        }
    }
}