import org.slf4j.LoggerFactory

sealed class DwarvenMineWorker {
    private val log = LoggerFactory.getLogger(javaClass)
    abstract val name: String
    fun action(vararg actions: Action) = actions.forEach {
        when (it) {
            Action.GO_TO_SLEEP -> log.info("{} goes to sleep.", name)
            Action.WAKE_UP -> log.info("{} wakes up.", name)
            Action.GO_HOME -> log.info("{} goes home.", name)
            Action.GO_TO_MINE -> log.info("{} goes to the mine.", name)
            Action.WORK -> work()
        }
    }

    abstract fun work()
    enum class Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}

class DwarvenCartOperator : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "Dwarf cart operator"
    override fun work() = log.info("{} moves gold chunks out of the mine.", name)
}

class DwarvenGoldDigger : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "Dwarf gold digger"
    override fun work() = log.info("{} digs for gold.", name)
}

class DwarvenTunnelDigger : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "Dwarven tunnel digger"
    override fun work() = log.info("{} creates another promising tunnel.", name)
}
