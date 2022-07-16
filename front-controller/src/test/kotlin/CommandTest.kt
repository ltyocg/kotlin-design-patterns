import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CommandTest {
    private val assertListAppender = assertListAppender {
        bind<View>(true)
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun display(request: String, displayMessage: String) {
        assertListAppender.clear()
        FrontController.handleRequest(request)
        assertEquals(displayMessage, assertListAppender.lastMessage())
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf("Archer", "Displaying archers"),
            arrayOf("Catapult", "Displaying catapults"),
            arrayOf("NonExistentCommand", "Error 500")
        )
    }
}