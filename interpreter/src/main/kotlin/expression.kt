abstract class Expression {
    abstract fun interpret(): Int
    abstract override fun toString(): String
}

class MinusExpression(
    private val leftExpression: Expression,
    private val rightExpression: Expression
) : Expression() {
    override fun interpret(): Int = leftExpression.interpret() - rightExpression.interpret()
    override fun toString(): String = "-"
}

class MultiplyExpression(
    private val leftExpression: Expression,
    private val rightExpression: Expression
) : Expression() {
    override fun interpret(): Int = leftExpression.interpret() * rightExpression.interpret()
    override fun toString(): String = "*"
}

class NumberExpression(private val number: Int) : Expression() {
    constructor(number: String) : this(number.toInt())

    override fun interpret(): Int = number
    override fun toString(): String = "number"
}

class PlusExpression(
    private val leftExpression: Expression,
    private val rightExpression: Expression
) : Expression() {
    override fun interpret(): Int = leftExpression.interpret() + rightExpression.interpret()
    override fun toString(): String = "+"
}

operator fun Expression.plus(rightExpression: Expression): PlusExpression = PlusExpression(this, rightExpression)
operator fun Expression.minus(rightExpression: Expression): MinusExpression = MinusExpression(this, rightExpression)
operator fun Expression.times(rightExpression: Expression): MultiplyExpression = MultiplyExpression(this, rightExpression)