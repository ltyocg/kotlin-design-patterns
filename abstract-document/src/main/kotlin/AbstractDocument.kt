abstract class AbstractDocument(properties: Map<String, Any?>) : Document {
    private val properties = properties.toMutableMap()

    override operator fun set(key: String, value: Any?) {
        properties[key] = value
    }

    override operator fun get(key: String): Any? = properties[key]

    @Suppress("UNCHECKED_CAST")
    override fun <T> children(key: String, constructor: (Map<String, Any?>) -> T): Sequence<T> =
        (this[key] as? List<Map<String, Any?>>)
            ?.asSequence()
            ?.map(constructor)
            ?: emptySequence()

    override fun toString(): String = javaClass.name + "[" + properties.entries.joinToString { "[${it.key} : ${it.value}]" } + "]"
}