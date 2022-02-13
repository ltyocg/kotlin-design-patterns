package com.ltyocg.bridge

import org.slf4j.LoggerFactory

interface Enchantment {
    fun onActivate()
    fun apply()
    fun onDeactivate()
}

class FlyingEnchantment : Enchantment {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun onActivate() {
        log.info("The item begins to glow faintly.")
    }

    override fun apply() {
        log.info("The item flies and strikes the enemies finally returning to owner's hand.")
    }

    override fun onDeactivate() {
        log.info("The item's glow fades.")
    }
}

class SoulEatingEnchantment : Enchantment {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun onActivate() {
        log.info("The item spreads bloodlust.")
    }

    override fun apply() {
        log.info("The item eats the soul of enemies.")
    }

    override fun onDeactivate() {
        log.info("Bloodlust slowly disappears.")
    }
}