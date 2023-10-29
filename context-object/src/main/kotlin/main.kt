import io.github.oshai.kotlinlogging.KotlinLogging

private const val SERVICE = "SERVICE"
private val logger = KotlinLogging.logger {}
fun main() {
    val layerA = LayerA()
    layerA.addAccountInfo(SERVICE)
    logger.info { "Context = ${layerA.context}" }
    val layerB = LayerB(layerA)
    layerB.addSessionInfo(SERVICE)
    logger.info { "Context = ${layerB.context}" }
    val layerC = LayerC(layerB)
    layerC.addSearchInfo(SERVICE)
    logger.info { "Context = ${layerC.context}" }
}
