class FilterChain {
    private var chain: Filter? = null
    fun addFilter(filter: Filter) {
        chain?.run { last.next = filter } ?: run { chain = filter }
    }

    fun execute(order: Order): String = chain?.execute(order) ?: "RUNNING..."
}