class WithThreadLocal(private val value: ThreadLocal<Int>) : AbstractThreadLocalExample() {
    fun remove() = value.remove()
    override fun setter(): (Int) -> Unit = value::set
    override fun getter(): () -> Int = value::get
}
