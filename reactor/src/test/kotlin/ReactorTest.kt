import app.App
import app.AppClient
import framework.SameThreadDispatcher
import framework.ThreadPoolDispatcher
import org.slf4j.LoggerFactory
import kotlin.test.Test

class ReactorTest {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun `app using thread pool dispatcher`() {
        log.info("testAppUsingThreadPoolDispatcher start")
        val app = App(ThreadPoolDispatcher(2))
        app.start()
        AppClient.start()
        AppClient.artificialDelayOf(2000)
        AppClient.stop()
        app.stop()
        log.info("testAppUsingThreadPoolDispatcher stop")
    }

    @Test
    fun `app using same thread dispatcher`() {
        log.info("testAppUsingSameThreadDispatcher start")
        val app = App(SameThreadDispatcher())
        app.start()
        AppClient.start()
        AppClient.artificialDelayOf(2000)
        AppClient.stop()
        app.stop()
        log.info("testAppUsingSameThreadDispatcher stop")
    }
}