package com.ltyocg.abstractdocument

abstract class AbstractDocument(properties: Map<String, Any?>) : Document {
    private val properties = properties.toMutableMap()
    override fun put(key: String, value: Any?) {
        properties[key] = value
    }

    override fun get(key: String): Any? = properties[key]
    override fun <T> children(key: String, constructor: (Map<String, Any?>) -> T): Sequence<T> {
        return sequenceOf(get(key))
            .filterNotNull()
            .map {
                @Suppress("UNCHECKED_CAST")
                it as List<Map<String, Any?>>
            }
            .firstOrNull()
            ?.asSequence()
            ?.map(constructor) ?: emptySequence()
    }

    override fun toString(): String = javaClass.name + "[" + properties.entries.joinToString { "[${it.key} : ${it.value}]" } + "]"
}