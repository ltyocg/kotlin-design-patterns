import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class PipelineTest {
    @Test
    fun `add handlers to pipeline`() = Assertions.assertArrayEquals(
        charArrayOf('#', '!', '(', '&', '%', '#', '!'),
        Pipeline(RemoveAlphabetsHandler())
            .addHandler(RemoveDigitsHandler())
            .addHandler(ConvertToCharArrayHandler())
            .execute("#H!E(L&L0O%THE3R#34E!")
    )
}