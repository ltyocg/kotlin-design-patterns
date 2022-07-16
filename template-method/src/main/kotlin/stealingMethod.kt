import org.slf4j.LoggerFactory

sealed class StealingMethod {
    private val log = LoggerFactory.getLogger(javaClass)
    abstract fun pickTarget(): String
    abstract fun confuseTarget(target: String)
    abstract fun stealTheItem(target: String)
    fun steal() {
        val target = pickTarget()
        log.info("The target has been chosen as {}.", target)
        confuseTarget(target)
        stealTheItem(target)
    }
}

class HitAndRunMethod : StealingMethod() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun pickTarget(): String = "old goblin woman"
    override fun confuseTarget(target: String) = log.info("Approach the {} from behind.", target)
    override fun stealTheItem(target: String) = log.info("Grab the handbag and run away fast!")
}

class SubtleMethod : StealingMethod() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun pickTarget(): String = "shop keeper"
    override fun confuseTarget(target: String) = log.info("Approach the {} with tears running and hug him!", target)
    override fun stealTheItem(target: String) = log.info("While in close contact grab the {}'s wallet.", target)
}