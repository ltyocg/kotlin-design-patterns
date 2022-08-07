import kotlin.test.Test
import kotlin.test.assertEquals

class RainbowFishTest {
    @Test
    fun values() {
        val fish = RainbowFish("name", 1, 2, 3)
        assertEquals("name", fish.name)
        assertEquals(1, fish.age)
        assertEquals(2, fish.lengthMeters)
        assertEquals(3, fish.weightTons)
    }
}