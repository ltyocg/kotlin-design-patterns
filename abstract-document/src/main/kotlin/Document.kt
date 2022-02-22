interface Document {
    fun put(key: String, value: Any?)
    fun get(key: String): Any?
    fun <T> children(key: String, constructor: (Map<String, Any?>) -> T): Sequence<T>
}