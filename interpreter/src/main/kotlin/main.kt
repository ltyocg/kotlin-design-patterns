import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}
fun main() {
    val stack = Stack<Expression>()
    "4 3 2 - 1 + *".split(" ").forEach {
        if (it == "+" || it == "-" || it == "*") {
            val rightExpression = stack.pop()
            val leftExpression = stack.pop()
            logger.info { "popped from stack left: ${leftExpression.interpret()} right: ${rightExpression.interpret()}" }
            val operator = when (it) {
                "+" -> leftExpression + rightExpression
                "-" -> leftExpression - rightExpression
                else -> leftExpression * rightExpression
            }
            logger.info { "operator: $operator" }
            val resultExpression = NumberExpression(operator.interpret())
            stack.push(resultExpression)
            logger.info { "push result to stack: ${resultExpression.interpret()}" }
        } else {
            val i = NumberExpression(it)
            stack.push(i)
            logger.info { "push to stack: ${i.interpret()}" }
        }
    }
    logger.info { "result: ${stack.pop().interpret()}" }
}
