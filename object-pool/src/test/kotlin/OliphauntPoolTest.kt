import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import kotlin.test.*

class OliphauntPoolTest {
    @Test
    fun `subsequent checkin checkout`() = assertTimeout(Duration.ofMillis(5000)) {
        val pool = OliphauntPool()
        assertEquals("Pool available=0 inUse=0", pool.toString())
        val expectedOliphaunt = pool.checkOut()
        assertEquals("Pool available=0 inUse=1", pool.toString())
        pool.checkIn(expectedOliphaunt)
        assertEquals("Pool available=1 inUse=0", pool.toString())
        repeat(100) {
            val oliphaunt = pool.checkOut()
            assertEquals("Pool available=0 inUse=1", pool.toString())
            assertSame(expectedOliphaunt, oliphaunt)
            assertEquals(expectedOliphaunt.id, oliphaunt.id)
            assertEquals(expectedOliphaunt.toString(), oliphaunt.toString())
            pool.checkIn(oliphaunt)
            assertEquals("Pool available=1 inUse=0", pool.toString())
        }
    }

    @Test
    fun `concurrent checkin checkout`() = assertTimeout(Duration.ofMillis(5000)) {
        val pool = OliphauntPool()
        assertEquals(pool.toString(), "Pool available=0 inUse=0")
        val firstOliphaunt = pool.checkOut()
        assertEquals(pool.toString(), "Pool available=0 inUse=1")
        val secondOliphaunt = pool.checkOut()
        assertEquals(pool.toString(), "Pool available=0 inUse=2")
        assertNotSame(firstOliphaunt, secondOliphaunt)
        assertEquals(firstOliphaunt.id + 1, secondOliphaunt.id)
        pool.checkIn(secondOliphaunt)
        assertEquals(pool.toString(), "Pool available=1 inUse=1")
        val oliphaunt3 = pool.checkOut()
        assertEquals(pool.toString(), "Pool available=0 inUse=2")
        assertSame(secondOliphaunt, oliphaunt3)
        pool.checkIn(firstOliphaunt)
        assertEquals(pool.toString(), "Pool available=1 inUse=1")
        val oliphaunt4 = pool.checkOut()
        assertEquals(pool.toString(), "Pool available=0 inUse=2")
        assertSame(firstOliphaunt, oliphaunt4)
        pool.checkIn(firstOliphaunt)
        assertEquals(pool.toString(), "Pool available=1 inUse=1")
        pool.checkIn(secondOliphaunt)
        assertEquals(pool.toString(), "Pool available=2 inUse=0")
        val oliphaunts = listOf(pool.checkOut(), pool.checkOut())
        assertEquals(pool.toString(), "Pool available=0 inUse=2")
        assertTrue(oliphaunts.contains(firstOliphaunt))
        assertTrue(oliphaunts.contains(secondOliphaunt))
    }
}