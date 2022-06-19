import com.ltyocg.commons.assertLogContains
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CommandTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun display(request: String, displayMessage: String) = assertLogContains(displayMessage) { FrontController.handleRequest(request) }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf("Archer", "Displaying archers"),
            arrayOf("Catapult", "Displaying catapults"),
            arrayOf("NonExistentCommand", "Error 500")
        )
    }
}