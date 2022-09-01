import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Start calculating war casualties")
    log.info("The number of orcs perished in the war: {}", loop(10, 1).result())
}

fun loop(times: Int, prod: Int): Trampoline<Int> =
    if (times == 0) Trampoline.done(prod)
    else Trampoline.more { loop(times - 1, prod * times) }