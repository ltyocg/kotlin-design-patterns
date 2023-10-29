import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class WashingMachine(
    private val delayProvider: DelayProvider = DelayProvider { interval, timeUnit, task ->
        delay(timeUnit.toMillis(interval))
        task()
    }
) {
    private val logger = KotlinLogging.logger {}
    var washingMachineState: WashingMachineState = WashingMachineState.ENABLED
    private val lock = ReentrantLock()
    suspend fun wash() {
        lock.withLock {
            logger.info { "${Thread.currentThread().name}: Actual machine state: $washingMachineState" }
            if (washingMachineState == WashingMachineState.WASHING) {
                logger.error { "Cannot wash if the machine has been already washing!" }
                return
            }
            washingMachineState = WashingMachineState.WASHING
        }
        logger.info { "${Thread.currentThread().name}: Doing the washing" }
        delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS) {
            lock.withLock {
                washingMachineState = WashingMachineState.ENABLED
                logger.info { "${Thread.currentThread().id}: Washing completed." }
            }
        }
    }
}
