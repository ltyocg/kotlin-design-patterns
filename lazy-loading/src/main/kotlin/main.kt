import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "heavy=${HolderNaive().heavy}" }
    logger.info { "another=${HolderThreadSafe().heavy}" }
    logger.info { "next=${LambdaHolder().heavy}" }
}
