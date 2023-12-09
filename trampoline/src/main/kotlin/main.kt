import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Start calculating war casualties" }
    logger.info { "The number of orcs perished in the war: ${loop(10, 1).result()}" }
}

fun loop(times: Int, prod: Int): Trampoline<Int> =
    if (times == 0) Trampoline.done(prod)
    else Trampoline.more { loop(times - 1, prod * times) }
