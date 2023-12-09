import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Program started" }
    val executor = Executors.newFixedThreadPool(3)
    listOf(
        PotatoPeelingTask(3),
        PotatoPeelingTask(6),
        CoffeeMakingTask(2),
        CoffeeMakingTask(6),
        PotatoPeelingTask(4),
        CoffeeMakingTask(2),
        PotatoPeelingTask(4),
        CoffeeMakingTask(9),
        PotatoPeelingTask(3),
        CoffeeMakingTask(2),
        PotatoPeelingTask(4),
        CoffeeMakingTask(2),
        CoffeeMakingTask(7),
        PotatoPeelingTask(4),
        PotatoPeelingTask(5)
    ).map { Runnable { Worker(it)() } }.forEach(executor::execute)
    executor.shutdown()
    while (!executor.isTerminated) Thread.yield()
    logger.info { "Program finished" }
}
