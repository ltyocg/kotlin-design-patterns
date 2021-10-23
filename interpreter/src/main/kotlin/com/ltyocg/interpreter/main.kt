package com.ltyocg.interpreter

import org.slf4j.LoggerFactory
import java.util.*

private val log = LoggerFactory.getLogger("main")

fun main() {
    val stack = Stack<Expression>()
    "4 3 2 - 1 + *".split(" ").forEach {
        if (it == "+" || it == "-" || it == "*") {
            val rightExpression = stack.pop()
            val leftExpression = stack.pop()
            log.info("popped from stack left: {} right: {}", leftExpression.interpret(), rightExpression.interpret())
            val operator = when (it) {
                "+" -> PlusExpression(leftExpression, rightExpression)
                "-" -> MinusExpression(leftExpression, rightExpression)
                else -> MultiplyExpression(leftExpression, rightExpression)
            }
            log.info("operator: {}", operator)
            val resultExpression = NumberExpression(operator.interpret())
            stack.push(resultExpression)
            log.info("push result to stack: {}", resultExpression.interpret())
        } else {
            val i = NumberExpression(it)
            stack.push(i)
            log.info("push to stack: {}", i.interpret())
        }
    }
    log.info("result: {}", stack.pop().interpret())
}