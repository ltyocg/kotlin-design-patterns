package com.ltyocg.adapter

import org.slf4j.LoggerFactory

class FishingBoat {
    private val log = LoggerFactory.getLogger(javaClass)
    fun sail() {
        log.info("The fishing boat is sailing")
    }
}

class FishingBoatAdapter : RowingBoat {
    private val boat = FishingBoat()
    override fun row() {
        boat.sail()
    }
}