package com.ltyocg.acyclicvisitor

import org.slf4j.LoggerFactory

abstract class Modem {
    abstract fun accept(modemVisitor: ModemVisitor)
}

class Hayes : Modem() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is HayesVisitor) modemVisitor.visit(this)
        else log.info("Only HayesVisitor is allowed to visit Hayes modem")
    }

    override fun toString(): String = "Hayes modem"
}

class Zoom : Modem() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is ZoomVisitor) modemVisitor.visit(this)
        else log.info("Only HayesVisitor is allowed to visit Zoom modem")
    }

    override fun toString(): String = "Zoom modem"
}