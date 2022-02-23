import org.slf4j.LoggerFactory

interface Potion {
    fun drink()
}

class HealingPotion : Potion {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun drink() {
        log.info("You feel healed. (Potion={})", System.identityHashCode(this))
    }
}

class HolyWaterPotion : Potion {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun drink() {
        log.info("You feel blessed. (Potion={})", System.identityHashCode(this))
    }
}

class InvisibilityPotion : Potion {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun drink() {
        log.info("You become invisible. (Potion={})", System.identityHashCode(this))
    }
}

class PoisonPotion : Potion {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun drink() {
        log.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this))
    }
}

class StrengthPotion : Potion {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun drink() {
        log.info("You feel strong. (Potion={})", System.identityHashCode(this))
    }
}