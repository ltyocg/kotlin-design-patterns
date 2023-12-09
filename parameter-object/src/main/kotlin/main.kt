import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val params = ParameterObject("sneakers", "brand")
    logger.info { params }
    logger.info { SearchService().search(params) }
}
