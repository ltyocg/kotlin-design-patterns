import com.ltyocg.commons.assertLogContains
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ViewTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `test display`(view: View, displayMessage: String) {
        assertLogContains(displayMessage) { view.display() }
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf(ArcherView(), "Displaying archers"),
            arrayOf(CatapultView(), "Displaying catapults"),
            arrayOf(ErrorView(), "Error 500")
        )
    }
}