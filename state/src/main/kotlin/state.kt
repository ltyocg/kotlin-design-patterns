import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface State {
    fun onEnterState()
    fun observe()
}

class AngryState(private val mammoth: Mammoth) : State {
    private val logger = KotlinLogging.logger {}
    override fun observe() = logger.info { "$mammoth is furious!" }
    override fun onEnterState() = logger.info { "$mammoth gets angry!" }
}

class PeacefulState(private val mammoth: Mammoth) : State {
    private val logger = KotlinLogging.logger {}
    override fun observe() = logger.info { "$mammoth is calm and peaceful." }
    override fun onEnterState() = logger.info { "$mammoth calms down." }
}
