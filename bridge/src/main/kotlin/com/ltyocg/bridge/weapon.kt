package com.ltyocg.bridge

import org.slf4j.LoggerFactory

interface Weapon {
    fun wield()
    fun swing()
    fun unwield()
    val enchantment: Enchantment
}

class Hammer(override val enchantment: Enchantment) : Weapon {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun wield() {
        log.info("The hammer is wielded.")
        enchantment.onActivate()
    }

    override fun swing() {
        log.info("The hammer is swung.")
        enchantment.apply()
    }

    override fun unwield() {
        log.info("The hammer is unwielded.")
        enchantment.onDeactivate()
    }
}

class Sword(override val enchantment: Enchantment) : Weapon {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun wield() {
        log.info("The sword is wielded.")
        enchantment.onActivate()
    }

    override fun swing() {
        log.info("The sword is swung.")
        enchantment.apply()
    }

    override fun unwield() {
        log.info("The sword is unwielded.")
        enchantment.onDeactivate()
    }
}
