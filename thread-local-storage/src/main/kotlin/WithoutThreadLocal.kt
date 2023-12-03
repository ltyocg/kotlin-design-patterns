class WithoutThreadLocal(private var value: Int) : AbstractThreadLocalExample() {
    override fun setter(): (Int) -> Unit = { value = it }
    override fun getter(): () -> Int = { value }
}
