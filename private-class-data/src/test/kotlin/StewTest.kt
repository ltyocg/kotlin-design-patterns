import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class StewTest {
    @Test
    fun mix() {
        val stew = ImmutableStew(1, 2, 3, 4)
        repeat(20) {
            assertLogContains("Mixing the immutable stew we find: 1 potatoes, 2 carrots, 3 meat and 4 peppers") {
                stew.mix()
            }
        }
    }
}