package com.ltyocg.dirtyflag

class World {
    private var countries = emptyList<String>()
    private val df = DataFetcher()
    fun fetch(): List<String> {
        df.fetch().also { if (it.isNotEmpty()) countries = it }
        return countries
    }
}