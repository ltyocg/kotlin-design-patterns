package com.ltyocg.abstractdocument

abstract class AbstractDocument(properties: Map<String, Any?>) : Document {
    private val properties = properties.toMutableMap()

    override fun put(key: String, value: Any?) {
        properties[key] = value
    }

    override fun get(key: String): Any? = properties[key]

    @Suppress("UNCHECKED_CAST")
    override fun <T> children(key: String, constructor: (Map<String, Any?>) -> T): Sequence<T> =
        (get(key) as? List<Map<String, Any?>>)
            ?.asSequence()
            ?.map(constructor)
            ?: emptySequence()

    override fun toString(): String = javaClass.name + "[" + properties.entries.joinToString { "[${it.key} : ${it.value}]" } + "]"
}