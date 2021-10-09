package com.ltyocg.fluentinterface

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
private val negatives = { integer: Int -> integer < 0 }
private val positives = { integer: Int -> integer > 0 }
private val transformToString = { integer: Int -> "String[$integer]" }

fun main() {
    val integerList = listOf(1, -61, 14, -22, 18, -87, 6, 64, -82, 26, -98, 97, 45, 23, 2, -68)
    integerList.prettyPrint("The initial list contains: ")
    integerList
        .filter(negatives)
        .take(3)
        .prettyPrint("The first three negative values are: ")
    integerList
        .filter(positives)
        .takeLast(2)
        .prettyPrint("The last two positive values are: ")
    integerList
        .firstOrNull { it % 2 == 0 }
        ?.let { log.info("The first even number is: {}", it) }
    integerList
        .filter(negatives)
        .map(transformToString)
        .prettyPrint("A string-mapped list of negative numbers contains: ")
    integerList.asSequence()
        .filter(positives)
        .drop(2)
        .take(2)
        .map(transformToString)
        .toList()
        .prettyPrint("The lazy list contains the last two of the first four positive numbers mapped to Strings: ")
    integerList.asSequence()
        .filter(negatives)
        .take(2)
        .lastOrNull()
        ?.let { log.info("Last amongst first two negatives: {}", it) }
}

private fun Iterable<*>.prettyPrint(prefix: String) {
    log.info(joinToString(prefix = prefix, postfix = "."))
}