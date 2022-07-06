import com.ltyocg.commons.assertListAppender
import kotlinx.coroutines.*
import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class InventoryTest {
    private companion object {
        private const val INVENTORY_SIZE = 1000
    }

    @Test
    fun addItem() = assertTimeout(Duration.ofMillis(10000)) {
        val coroutineDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()
        runBlocking(coroutineDispatcher) {
            val inventory = Inventory(INVENTORY_SIZE)
            try {
                val assertListAppender = assertListAppender(Inventory::class)
                withTimeoutOrNull(TimeUnit.SECONDS.toMillis(5)) {
                    repeat(8) {
                        launch {
                            @Suppress("ControlFlowWithEmptyBody")
                            while (inventory.addItem(Item()));
                        }
                    }
                }
                assertEquals(INVENTORY_SIZE, inventory.items.size)
                val logList = assertListAppender.list
                assertEquals(INVENTORY_SIZE, logList.size)
                logList.forEachIndexed { i, iLoggingEvent ->
                    assertContains(iLoggingEvent.formattedMessage, "items.size=${i + 1}")
                }
            } catch (_: TimeoutCancellationException) {
            }
            coroutineDispatcher.close()
        }
    }
}