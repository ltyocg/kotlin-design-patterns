interface Document {
    operator fun set(key: String, value: Any?)
    operator fun get(key: String): Any?
    fun <T> children(key: String, constructor: (Map<String, Any?>) -> T): Sequence<T>
}