package com.ltyocg.commons

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
import org.slf4j.MDC
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.assertContains
import kotlin.test.assertContentEquals

fun assertLogContains(expected: String, block: () -> Unit) {
    require(expected.isNotEmpty())
    assertContains(logContents(block), expected)
}

fun assertLogContentEquals(expected: Iterable<String?>, block: () -> Unit) =
    assertContentEquals(expected, logContents(block))

fun logContents(block: () -> Unit): List<String> = logAop(block).map { it.formattedMessage }
private const val KEY_PREFIX = "log_"
private val logAopMap = ConcurrentHashMap<String, MutableList<ILoggingEvent>>()
private fun logAop(block: () -> Unit): List<ILoggingEvent> {
    val key = "$KEY_PREFIX${UUID.randomUUID()}"
    logAopMap[key] = mutableListOf()
    MDC.put(key, System.currentTimeMillis().toString())
    block()
    MDC.remove(key)
    return logAopMap.remove(key)!!
}

suspend fun logAopCoroutines(block: suspend CoroutineScope.() -> Unit): List<ILoggingEvent> {
    val key = "$KEY_PREFIX${UUID.randomUUID()}"
    logAopMap[key] = mutableListOf()
    withContext(MDCContext(mapOf(key to System.currentTimeMillis().toString())), block)
    return logAopMap.remove(key)!!
}

class AssertConsoleAppender : ConsoleAppender<ILoggingEvent>() {
    override fun subAppend(event: ILoggingEvent) {
        event.mdcPropertyMap.keys.asSequence()
            .filter { it.startsWith(KEY_PREFIX) }
            .mapNotNull { logAopMap[it] }
            .forEach { it.add(event) }
        super.subAppend(event)
    }
}