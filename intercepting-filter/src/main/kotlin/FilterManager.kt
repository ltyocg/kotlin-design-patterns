class FilterManager {
    private val filterChain = FilterChain()
    fun addFilter(filter: Filter) {
        filterChain.addFilter(filter)
    }

    fun filterRequest(order: Order): String = filterChain.execute(order)
}