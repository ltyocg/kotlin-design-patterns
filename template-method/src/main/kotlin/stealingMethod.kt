import io.github.oshai.kotlinlogging.KotlinLogging

sealed class StealingMethod {
    private val logger = KotlinLogging.logger {}
    abstract fun pickTarget(): String
    abstract fun confuseTarget(target: String)
    abstract fun stealTheItem(target: String)
    fun steal() {
        val target = pickTarget()
        logger.info { "The target has been chosen as $target." }
        confuseTarget(target)
        stealTheItem(target)
    }
}

class HitAndRunMethod : StealingMethod() {
    private val logger = KotlinLogging.logger {}
    override fun pickTarget(): String = "old goblin woman"
    override fun confuseTarget(target: String) = logger.info { "Approach the $target from behind." }
    override fun stealTheItem(target: String) = logger.info { "Grab the handbag and run away fast!" }
}

class SubtleMethod : StealingMethod() {
    private val logger = KotlinLogging.logger {}
    override fun pickTarget(): String = "shop keeper"
    override fun confuseTarget(target: String) = logger.info { "Approach the $target with tears running and hug him!" }
    override fun stealTheItem(target: String) = logger.info { "While in close contact grab the $target's wallet." }
}
