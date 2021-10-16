package com.ltyocg.intercepting.filter

class FilterChain {
    private var chain: Filter? = null
    fun addFilter(filter: Filter) {
        if (chain != null) chain!!.last.next = filter
        else chain = filter
    }

    fun execute(order: Order): String = chain?.execute(order) ?: "RUNNING..."
}