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

fun assertLogContains(expected: String, block: LogContext.() -> Unit) = assertLogContains(listOf(expected), block)
fun assertLogContains(expected: Iterable<String?>, block: LogContext.() -> Unit) {
    val contents = logContents(block)
    expected.forEach { assertContains(contents, it) }
}

fun assertLogContentEquals(expected: Iterable<String?>, block: LogContext.() -> Unit) =
    assertContentEquals(expected, logContents(block))

fun logContents(block: LogContext.() -> Unit): List<String> {
    val logContext = LogContext()
    logAopMap[logContext.key] = mutableListOf()
    MDC.put(logContext.key, System.currentTimeMillis().toString())
    block(logContext)
    MDC.remove(logContext.key)
    return logAopMap.remove(logContext.key)!!.map { it.formattedMessage }
}

suspend fun logAopCoroutines(block: suspend CoroutineScope.() -> Unit): List<ILoggingEvent> {
    val key = "$KEY_PREFIX${UUID.randomUUID()}"
    logAopMap[key] = mutableListOf()
    withContext(MDCContext(mapOf(key to System.currentTimeMillis().toString())), block)
    return logAopMap.remove(key)!!
}

private const val KEY_PREFIX = "log_"
private val logAopMap = ConcurrentHashMap<String, MutableList<ILoggingEvent>>()

class LogContext internal constructor() {
    val key = "$KEY_PREFIX${UUID.randomUUID()}"
    fun wrap(block: () -> Unit): () -> Unit = {
        MDC.put(key, System.currentTimeMillis().toString())
        block()
        MDC.remove(key)
    }
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