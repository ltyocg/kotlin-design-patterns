import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val params = ParameterObject("sneakers", "brand")
    log.info(params.toString())
    log.info(SearchService().search(params))
}