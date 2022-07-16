import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class StealingMethodTest<M : StealingMethod>(
    private val method: M,
    private val expectedTarget: String,
    private val expectedTargetResult: String,
    private val expectedConfuseMethod: String,
    private val expectedStealMethod: String
) {
    @Test
    fun pickTarget() = assertEquals(expectedTarget, method.pickTarget())

    @Test
    fun confuseTarget() {
        val assertListAppender = assertListAppender(method::class)
        assertTrue(assertListAppender.list.isEmpty())
        method.confuseTarget(expectedTarget)
        assertContentEquals(listOf(expectedConfuseMethod), assertListAppender.formattedList())
    }

    @Test
    fun stealTheItem() {
        val assertListAppender = assertListAppender(method::class)
        assertTrue(assertListAppender.list.isEmpty())
        method.stealTheItem(expectedTarget)
        assertContentEquals(listOf(expectedStealMethod), assertListAppender.formattedList())
    }

    @Test
    fun testSteal() {
        val assertListAppender = assertListAppender {
            bind<StealingMethod>(true)
        }
        method.steal()
        assertContentEquals(
            listOf(expectedTargetResult, expectedConfuseMethod, expectedStealMethod),
            assertListAppender.formattedList()
        )
    }
}