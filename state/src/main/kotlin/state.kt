import org.slf4j.LoggerFactory

sealed interface State {
    fun onEnterState()
    fun observe()
}

class AngryState(private val mammoth: Mammoth) : State {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun observe() = log.info("{} is furious!", mammoth)
    override fun onEnterState() = log.info("{} gets angry!", mammoth)
}

class PeacefulState(private val mammoth: Mammoth) : State {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun observe() = log.info("{} is calm and peaceful.", mammoth)
    override fun onEnterState() = log.info("{} calms down.", mammoth)
}