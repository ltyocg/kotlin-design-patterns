import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FrontControllerTest {
    private val assertListAppender = assertListAppender {
        bind<View>(true)
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun display(command: Command, displayMessage: String) {
        assertListAppender.clear()
        command.process()
        assertEquals(displayMessage, assertListAppender.lastMessage())
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf(ArcherCommand, "Displaying archers"),
            arrayOf(CatapultCommand, "Displaying catapults"),
            arrayOf(UnknownCommand, "Error 500")
        )
    }
}