import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ExpressionTest<E : Expression>(
    private val expectedToString: String,
    private val factory: (NumberExpression, NumberExpression) -> E
) {
    abstract fun expressionProvider(): Iterator<Arguments>

    @ParameterizedTest
    @MethodSource("expressionProvider")
    fun `test interpret`(first: NumberExpression, second: NumberExpression, result: Int) {
        assertEquals(result, factory(first, second).interpret())
    }

    @ParameterizedTest
    @MethodSource("expressionProvider")
    fun `test toString`(first: NumberExpression, second: NumberExpression) {
        assertEquals(expectedToString, factory(first, second).toString())
    }
}

class MinusExpressionTest : ExpressionTest<MinusExpression>("-", ::MinusExpression) {
    override fun expressionProvider(): Iterator<Arguments> = prepareParameters { f, s -> f - s }
}

class MultiplyExpressionTest : ExpressionTest<MultiplyExpression>("*", ::MultiplyExpression) {
    override fun expressionProvider(): Iterator<Arguments> = prepareParameters { f, s -> f * s }
}

class NumberExpressionTest : ExpressionTest<NumberExpression>("number", { f, _ -> f }) {
    override fun expressionProvider(): Iterator<Arguments> = prepareParameters { f, _ -> f }

    @ParameterizedTest
    @MethodSource("expressionProvider")
    fun testFromString(first: NumberExpression) {
        val expectedValue = first.interpret()
        assertEquals(expectedValue, NumberExpression(expectedValue.toString()).interpret())
    }
}

class PlusExpressionTest : ExpressionTest<PlusExpression>("+", ::PlusExpression) {
    override fun expressionProvider(): Iterator<Arguments> = prepareParameters { f, s -> f + s }
}

private fun prepareParameters(resultCalc: (Int, Int) -> Int): Iterator<Arguments> = sequence {
    for (i in -10 until 10) for (j in -10 until 10) yield(Arguments.of(NumberExpression(i), NumberExpression(j), resultCalc(i, j)))
}.iterator()