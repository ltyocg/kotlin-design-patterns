package domain

import Lockable
import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom

class Feind(
    private val creature: Creature,
    private val target: Lockable
) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    private val random = SecureRandom()
    override fun invoke() =
        if (creature.acquire(target)) logger.info { "${target.locker!!.name} has acquired the sword!" }
        else try {
            fightForTheSword(creature, target.locker!!, target)
        } catch (e: InterruptedException) {
            logger.error { e.localizedMessage }
            Thread.currentThread().interrupt()
        }

    private fun fightForTheSword(reacher: Creature, holder: Creature, sword: Lockable) {
        logger.info { "A duel between ${reacher.name} and ${holder.name} has been started!" }
        while (target.isLocked && reacher.isAlive && holder.isAlive) {
            if (random.nextBoolean()) reacher.attack(holder)
            else holder.attack(reacher)
        }
        if (reacher.isAlive)
            if (reacher.acquire(sword)) logger.info { "${reacher.name} has acquired the sword!" }
            else fightForTheSword(reacher, sword.locker!!, sword)
    }
}
