import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class BallItemTest {
    @Test
    fun click() {
        val ballThread = mock<BallThread>()
        val ballItem = BallItem()
        ballItem.twin = ballThread
        val inOrder = inOrder(ballThread)
        repeat(10) {
            ballItem.click()
            inOrder.verify(ballThread).suspendMe()
            ballItem.click()
            inOrder.verify(ballThread).resumeMe()
        }
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun doDraw() {
        val assertListAppender = assertListAppender {
            bind<BallItem>()
            bind<GameItem>()
        }
        val ballItem = BallItem()
        val ballThread = mock<BallThread>()
        ballItem.twin = ballThread
        ballItem.draw()
        val formattedList = assertListAppender.formattedList()
        assertContains(formattedList, "draw")
        assertContains(formattedList, "doDraw")
        verifyNoMoreInteractions(ballThread)
        assertEquals(2, formattedList.size)
    }

    @Test
    fun move() {
        val assertListAppender = assertListAppender(BallItem::class)
        val ballItem = BallItem()
        val ballThread = mock<BallThread>()
        ballItem.twin = ballThread
        ballItem.move()
        assertContentEquals(listOf("move"), assertListAppender.formattedList())
        verifyNoMoreInteractions(ballThread)
    }
}