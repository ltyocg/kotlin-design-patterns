import io.github.oshai.kotlinlogging.KotlinLogging

private const val GAME_RUNNING_TIME = 2000L
private val logger = KotlinLogging.logger {}
fun main() {
    try {
        with(World()) {
            addEntity(Skeleton(1))
            addEntity(Skeleton(2))
            addEntity(Statue(3, 20))
            run()
            Thread.sleep(GAME_RUNNING_TIME)
            stop()
        }
    } catch (e: InterruptedException) {
        logger.error { e.localizedMessage }
    }
}
