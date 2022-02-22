import kotlin.test.Test
import kotlin.test.assertEquals

class FinderTest {
    @Test
    fun contains() {
        val result = Finder.contains("second").find("the first one \nthe second one \n")
        assertEquals(1, result.size)
        assertEquals("the second one ", result[0])
    }
}