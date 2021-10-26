package com.ltyocg.layers

import org.slf4j.LoggerFactory

class CakeView(private val cakeBakingService: CakeBakingService) {
    private val log = LoggerFactory.getLogger(this::class.java)
    fun render() {
        cakeBakingService.getAllCakes().forEach { log.info(it.toString()) }
    }
}