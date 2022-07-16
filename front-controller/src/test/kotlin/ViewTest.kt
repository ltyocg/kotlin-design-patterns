import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ViewTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun display(view: View, displayMessage: String) {
        val assertListAppender = assertListAppender(view::class)
        view.display()
        assertEquals(displayMessage, assertListAppender.lastMessage())
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf(ArcherView, "Displaying archers"),
            arrayOf(CatapultView, "Displaying catapults"),
            arrayOf(ErrorView, "Error 500")
        )
    }
}