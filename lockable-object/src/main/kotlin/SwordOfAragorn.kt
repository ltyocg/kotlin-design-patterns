import domain.Creature
import io.github.oshai.kotlinlogging.KotlinLogging

class SwordOfAragorn : Lockable {
    private val logger = KotlinLogging.logger {}
    private var _locker: Creature? = null
    private val synchronizer: Any = Any()
    override val isLocked: Boolean
        get() = _locker != null

    override fun lock(creature: Creature): Boolean {
        synchronized(synchronizer) {
            logger.info { "${creature.name} is now trying to acquire $name!" }
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
            logger.info { "$name is now free!" }
        }
        if (locker != null) throw LockingException("You cannot unlock an object you are not the owner of.")
    }

    override val locker: Creature?
        get() = _locker
    override val name: String = "The Sword of Aragorn"
}
