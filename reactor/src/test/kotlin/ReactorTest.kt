import app.App
import app.AppClient
import framework.SameThreadDispatcher
import framework.ThreadPoolDispatcher
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.test.Test

class ReactorTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `app using thread pool dispatcher`() {
        logger.info { "testAppUsingThreadPoolDispatcher start" }
        val app = App(ThreadPoolDispatcher(2))
        app.start()
        AppClient.start()
        AppClient.artificialDelayOf(2000)
        AppClient.stop()
        app.stop()
        logger.info { "testAppUsingThreadPoolDispatcher stop" }
    }

    @Test
    fun `app using same thread dispatcher`() {
        logger.info { "testAppUsingSameThreadDispatcher start" }
        val app = App(SameThreadDispatcher())
        app.start()
        AppClient.start()
        AppClient.artificialDelayOf(2000)
        AppClient.stop()
        app.stop()
        logger.info { "testAppUsingSameThreadDispatcher stop" }
    }
}
