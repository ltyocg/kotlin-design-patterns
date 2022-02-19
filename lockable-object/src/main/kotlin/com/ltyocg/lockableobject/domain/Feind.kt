package com.ltyocg.lockableobject.domain

import com.ltyocg.lockableobject.Lockable
import org.slf4j.LoggerFactory
import java.security.SecureRandom

class Feind(
    private val creature: Creature,
    private val target: Lockable
) : () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    private val random = SecureRandom()
    override fun invoke() =
        if (creature.acquire(target)) log.info("{} has acquired the sword!", target.locker!!.name)
        else try {
            fightForTheSword(creature, target.locker!!, target)
        } catch (e: InterruptedException) {
            log.error(e.localizedMessage)
            Thread.currentThread().interrupt()
        }

    private fun fightForTheSword(reacher: Creature, holder: Creature, sword: Lockable) {
        log.info("A duel between {} and {} has been started!", reacher.name, holder.name)
        while (target.isLocked && reacher.isAlive && holder.isAlive) {
            if (random.nextBoolean()) reacher.attack(holder)
            else holder.attack(reacher)
        }
        if (reacher.isAlive)
            if (reacher.acquire(sword)) log.info("{} has acquired the sword!", reacher.name)
            else fightForTheSword(reacher, sword.locker!!, sword)
    }
}