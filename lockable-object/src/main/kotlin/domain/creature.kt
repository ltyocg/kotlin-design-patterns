package domain

import Lockable
import io.github.oshai.kotlinlogging.KotlinLogging

abstract class Creature(
    val name: String,
    val type: CreatureType,
    var health: Int,
    val damage: Int
) {
    private val logger = KotlinLogging.logger {}
    val instruments: MutableSet<Lockable> = mutableSetOf()
    fun acquire(lockable: Lockable): Boolean = if (lockable.lock(this)) {
        instruments.add(lockable)
        true
    } else false

    @Synchronized
    fun kill() {
        logger.info { "$type $name has been slayed!" }
        instruments.forEach { it.unlock(this) }
        instruments.clear()
    }

    @Synchronized
    fun attack(creature: Creature) = creature.hit(damage)

    @Synchronized
    fun hit(damage: Int) {
        require(damage >= 0) { "Damage cannot be a negative number" }
        if (isAlive) {
            health -= damage
            if (!isAlive) kill()
        }
    }

    val isAlive: Boolean
        @Synchronized
        get() = health > 0
}

class Elf(name: String) : Creature(name, CreatureType.ELF, CreatureStats.ELF_HEALTH.value, CreatureStats.ELF_DAMAGE.value)
class Human(name: String) : Creature(name, CreatureType.HUMAN, CreatureStats.HUMAN_HEALTH.value, CreatureStats.HUMAN_DAMAGE.value)
class Orc(name: String) : Creature(name, CreatureType.ORC, CreatureStats.ORC_HEALTH.value, CreatureStats.ORC_DAMAGE.value)
