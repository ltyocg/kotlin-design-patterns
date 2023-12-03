import domain.Elf
import domain.Feind
import domain.Human
import domain.Orc
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val WORKERS = 2
private const val MULTIPLICATION_FACTOR = 3
private val logger = KotlinLogging.logger {}
fun main() {
    val sword = SwordOfAragorn()
    val creatures = buildList {
        repeat(WORKERS) {
            add(Elf("Elf $it"))
            add(Orc("Orc $it"))
            add(Human("Human $it"))
        }
    }
    val totalFiends = WORKERS * MULTIPLICATION_FACTOR
    val service = Executors.newFixedThreadPool(totalFiends)
    for (i in 0 until totalFiends step MULTIPLICATION_FACTOR) {
        service.submit(Feind(creatures[i], sword))
        service.submit(Feind(creatures[i + 1], sword))
        service.submit(Feind(creatures[i + 2], sword))
    }
    try {
        if (!service.awaitTermination(3, TimeUnit.SECONDS))
            logger.info { "The master of the sword is now ${sword.locker!!.name}." }
    } catch (e: InterruptedException) {
        logger.error { e.localizedMessage }
        Thread.currentThread().interrupt()
    } finally {
        service.shutdown()
    }
}
