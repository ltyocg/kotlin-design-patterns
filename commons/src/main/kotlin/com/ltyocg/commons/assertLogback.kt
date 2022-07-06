package com.ltyocg.commons

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.read.ListAppender
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.test.assertContains

fun assertLogContains(expected: String, block: LogContext.() -> Unit) = assertLogContains(listOf(expected), block)
fun assertLogContains(expected: Iterable<String?>, block: LogContext.() -> Unit) {
    val contents = logContents(block)
    expected.forEach { assertContains(contents, it) }
}

private fun logContents(block: LogContext.() -> Unit): List<String> {
    val logContext = LogContext()
    logAopMap[logContext.key] = mutableListOf()
    MDC.put(logContext.key, System.currentTimeMillis().toString())
    block(logContext)
    MDC.remove(logContext.key)
    return logAopMap.remove(logContext.key)!!.map { it.formattedMessage }
}

private const val KEY_PREFIX = "log_"
private val logAopMap = ConcurrentHashMap<String, MutableList<ILoggingEvent>>()

class LogContext internal constructor() {
    val key = "$KEY_PREFIX${UUID.randomUUID()}"
    val messageList: List<String>
        get() = logAopMap[key]!!.map { it.formattedMessage }

    fun clear() {
        logAopMap[key] = mutableListOf()
    }

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

fun assertListAppender(vararg kClass: KClass<*>): ListAppender<ILoggingEvent> =
    ListAppender<ILoggingEvent>().apply {
        start()
        kClass.forEach { (LoggerFactory.getLogger(it.java) as Logger).addAppender(this) }
    }

fun assertListAppender(builderAction: ListAppenderBuilder.() -> Unit): ListAppender<ILoggingEvent> =
    assertListAppender(*ListAppenderBuilder().apply(builderAction).list.toTypedArray())

class ListAppenderBuilder
internal constructor() {
    @PublishedApi
    internal val log = LoggerFactory.getLogger(javaClass)

    @PublishedApi
    internal val list = mutableListOf<KClass<*>>()
    inline fun <reified T> bind(sealedSubclasses: Boolean = false) {
        val kClass = T::class
        list.add(kClass)
        if (sealedSubclasses) {
            if (!kClass.isSealed) log.warn("{} is not a sealed class", kClass)
            kClass.sealedSubclasses.forEach(list::add)
        }
    }
}

fun ListAppender<ILoggingEvent>.formattedList(): List<String> = list.map { it.formattedMessage }
fun ListAppender<ILoggingEvent>.clear() = list.clear()