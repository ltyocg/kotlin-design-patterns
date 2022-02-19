package com.ltyocg.lockableobject

import com.ltyocg.lockableobject.domain.Creature
import org.slf4j.LoggerFactory

class SwordOfAragorn : Lockable {
    private val log = LoggerFactory.getLogger(javaClass)
    private var _locker: Creature? = null
    private val synchronizer: Any = Any()
    override val isLocked: Boolean
        get() = _locker != null

    override fun lock(creature: Creature): Boolean {
        synchronized(synchronizer) {
            log.info("{} is now trying to acquire {}!", creature.name, name)
            if (!isLocked) {
                _locker = creature
                return true
            } else if (locker!!.name != creature.name) return false
        }
        return false
    }

    override fun unlock(creature: Creature) = synchronized(synchronizer) {
        if (locker?.name == creature.name) {
            _locker = null
            log.info("{} is now free!", name)
        }
        if (locker != null) throw LockingException("You cannot unlock an object you are not the owner of.")
    }

    override val locker: Creature?
        get() = _locker
    override val name: String = "The Sword of Aragorn"
}