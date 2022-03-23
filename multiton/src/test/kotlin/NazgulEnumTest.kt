import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class NazgulEnumTest {
    @ParameterizedTest
    @EnumSource
    fun `the same object is returned with multiple calls`(nazgulEnum: NazgulEnum) = Assertions.assertSame(nazgulEnum, nazgulEnum)
}