import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class RetryTest {
    @Test
    fun perform() = runBlocking {
        val order = Order(User("Jim", "ABCD"), "book", 10f)
        val arr1 = mutableListOf(ItemUnavailableException(), DatabaseUnavailableException())
        retry().perform(arr1, order)
        val arr2 = mutableListOf(DatabaseUnavailableException(), ItemUnavailableException())
        retry().perform(arr2, order)
        assertTrue(arr1.size == 1 && arr2.isEmpty())
    }

    private fun retry(): Retry<Order> = Retry(
        { if (it.isNotEmpty()) throw it.removeAt(0) },
        { _, _ -> },
        3,
        30000,
        { it is DatabaseUnavailableException }
    )
}