import org.junit.jupiter.api.Assertions
import org.mockito.kotlin.*
import java.time.Duration
import kotlin.test.Test

class BallThreadTest {
    @Test
    fun suspend() = Assertions.assertTimeout(Duration.ofMillis(5000)) {
        val ballThread = BallThread()
        val ballItem = mock<BallItem>()
        ballThread.twin = ballItem
        ballThread.start()
        Thread.sleep(200)
        verify(ballItem, atLeastOnce()).draw()
        verify(ballItem, atLeastOnce()).move()
        ballThread.suspendMe()
        Thread.sleep(1000)
        ballThread.stopMe()
        ballThread.join()
        verifyNoMoreInteractions(ballItem)
    }

    @Test
    fun resume() = Assertions.assertTimeout(Duration.ofMillis(5000)) {
        val ballThread = BallThread()
        val ballItem = mock<BallItem>()
        ballThread.twin = ballItem
        ballThread.suspendMe()
        ballThread.start()
        Thread.sleep(1000)
        verifyNoMoreInteractions(ballItem)
        ballThread.resumeMe()
        Thread.sleep(300)
        verify(ballItem, atLeastOnce()).draw()
        verify(ballItem, atLeastOnce()).move()
        ballThread.stopMe()
        ballThread.join()
        verifyNoMoreInteractions(ballItem)
    }

    @Test
    fun interrupt() = Assertions.assertTimeout(Duration.ofMillis(5000)) {
        val ballThread = BallThread()
        val exceptionHandler = mock<Thread.UncaughtExceptionHandler>()
        ballThread.uncaughtExceptionHandler = exceptionHandler
        ballThread.twin = mock()
        ballThread.start()
        ballThread.interrupt()
        ballThread.join()
        verify(exceptionHandler).uncaughtException(eq(ballThread), any())
        verifyNoMoreInteractions(exceptionHandler)
    }
}