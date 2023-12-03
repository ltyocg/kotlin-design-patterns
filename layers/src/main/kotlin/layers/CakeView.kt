package layers

import io.github.oshai.kotlinlogging.KotlinLogging

class CakeView(private val cakeBakingService: CakeBakingService) {
    private val logger = KotlinLogging.logger {}
    fun render() = cakeBakingService.getAllCakes().forEach { logger.info { it } }
}
