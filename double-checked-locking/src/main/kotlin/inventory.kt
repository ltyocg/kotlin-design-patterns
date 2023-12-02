import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Inventory(private val inventorySize: Int) {
    private val logger = KotlinLogging.logger {}
    private val _items = ArrayList<Item>(inventorySize)
    private val lock = ReentrantLock()

    fun addItem(item: Item): Boolean {
        if (_items.size < inventorySize) lock.withLock {
            if (_items.size < inventorySize) {
                _items.add(item)
                logger.info { "${Thread.currentThread()}: items.size=${_items.size}, inventorySize=$inventorySize" }
                return true
            }
        }
        return false
    }

    val items: List<Item>
        get() = _items.toList()
}

class Item
