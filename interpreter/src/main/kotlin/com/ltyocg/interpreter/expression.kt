package com.ltyocg.interpreter

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