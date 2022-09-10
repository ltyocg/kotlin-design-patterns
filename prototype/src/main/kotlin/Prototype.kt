abstract class Prototype<T> : Cloneable {
    @Suppress("UNCHECKED_CAST")
    fun copy(): T = super.clone() as T
}