package com.ltyocg.doublechecked.locking

import com.ltyocg.commons.logAopCoroutines
import kotlinx.coroutines.*
import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

private const val THREAD_COUNT = 8
private const val INVENTORY_SIZE = 1000

class InventoryTest {
    @Test
    fun `test addItem`() {
        assertTimeout(Duration.ofMillis(10000)) {
            val coroutineDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()
            runBlocking(coroutineDispatcher) {
                val inventory = Inventory(INVENTORY_SIZE)
                try {
                    val logList = withTimeoutOrNull(TimeUnit.SECONDS.toMillis(5)) {
                        logAopCoroutines {
                            repeat(THREAD_COUNT) {
                                launch {
                                    @Suppress("ControlFlowWithEmptyBody")
                                    while (inventory.addItem(Item()));
                                }
                            }
                        }
                    }!!
                    assertEquals(INVENTORY_SIZE, inventory.items.size)
                    assertEquals(INVENTORY_SIZE, logList.size)
                    repeat(inventory.items.size) {
                        assertContains(logList[it].formattedMessage, "items.size=${it + 1}")
                    }
                } catch (e: TimeoutCancellationException) {
                }
                coroutineDispatcher.close()
            }
        }
    }
}