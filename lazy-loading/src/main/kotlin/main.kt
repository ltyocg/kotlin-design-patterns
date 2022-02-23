import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("heavy={}", HolderNaive().heavy)
    log.info("another={}", HolderThreadSafe().heavy)
    log.info("next={}", LambdaHolder().heavy)
}