import com.ltyocg.commons.assertLogContains
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class FrontControllerTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `test display`(command: Command, displayMessage: String) {
        assertLogContains(displayMessage) { command.process() }
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf(ArcherCommand(), "Displaying archers"),
            arrayOf(CatapultCommand(), "Displaying catapults"),
            arrayOf(UnknownCommand(), "Error 500")
        )
    }
}