import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import kotlin.test.assertSame

class NazgulEnumTest {
    @ParameterizedTest
    @EnumSource
    fun `the same object is returned with multiple calls`(nazgulEnum: NazgulEnum) = assertSame(nazgulEnum, nazgulEnum)
}