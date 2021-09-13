package com.ltyocg.dependency.injection

import org.slf4j.LoggerFactory

abstract class Tobacco {
    private val log = LoggerFactory.getLogger(this::class.java)
    fun smoke(wizard: Wizard) {
        log.info("{} smoking {}", wizard::class.simpleName, this::class.simpleName)
    }
}

class OldTobyTobacco : Tobacco()
class RivendellTobacco : Tobacco()
class SecondBreakfastTobacco : Tobacco()