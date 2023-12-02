import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "The alchemist begins his work." }
    logger.info { CoinFactory.getCoin(CoinType.COPPER).description }
    logger.info { CoinFactory.getCoin(CoinType.GOLD).description }
}
