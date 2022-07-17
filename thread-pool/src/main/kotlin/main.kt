import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Program started")
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
    log.info("Program finished")
}