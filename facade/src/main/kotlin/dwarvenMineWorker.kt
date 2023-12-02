import io.github.oshai.kotlinlogging.KotlinLogging

sealed class DwarvenMineWorker {
    private val logger = KotlinLogging.logger {}
    abstract val name: String
    fun action(vararg actions: Action) = actions.forEach {
        when (it) {
            Action.GO_TO_SLEEP -> logger.info { "$name goes to sleep." }
            Action.WAKE_UP -> logger.info { "$name wakes up." }
            Action.GO_HOME -> logger.info { "$name goes home." }
            Action.GO_TO_MINE -> logger.info { "$name goes to the mine." }
            Action.WORK -> work()
        }
    }

    abstract fun work()
    enum class Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}

class DwarvenCartOperator : DwarvenMineWorker() {
    private val logger = KotlinLogging.logger {}
    override val name = "Dwarf cart operator"
    override fun work() = logger.info { "$name moves gold chunks out of the mine." }
}

class DwarvenGoldDigger : DwarvenMineWorker() {
    private val logger = KotlinLogging.logger {}
    override val name = "Dwarf gold digger"
    override fun work() = logger.info { "$name digs for gold." }
}

class DwarvenTunnelDigger : DwarvenMineWorker() {
    private val logger = KotlinLogging.logger {}
    override val name = "Dwarven tunnel digger"
    override fun work() = logger.info { "$name creates another promising tunnel." }
}
