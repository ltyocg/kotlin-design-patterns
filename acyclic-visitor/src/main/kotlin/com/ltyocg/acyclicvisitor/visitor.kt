package com.ltyocg.acyclicvisitor

import org.slf4j.LoggerFactory

interface ModemVisitor

interface HayesVisitor : ModemVisitor {
    fun visit(hayes: Hayes)
}

interface ZoomVisitor : ModemVisitor {
    fun visit(zoom: Zoom)
}

interface AllModemVisitor : HayesVisitor, ZoomVisitor

class ConfigureForDosVisitor : AllModemVisitor {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun visit(hayes: Hayes) {
        log.info("{} used with Dos configurator.", hayes)
    }

    override fun visit(zoom: Zoom) {
        log.info("{} used with Dos configurator.", zoom)
    }
}

class ConfigureForUnixVisitor : ZoomVisitor {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun visit(zoom: Zoom) {
        log.info("{} used with Unix configurator.", zoom)
    }
}