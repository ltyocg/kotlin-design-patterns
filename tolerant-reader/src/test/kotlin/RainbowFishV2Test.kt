import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RainbowFishV2Test {
    @Test
    fun values() {
        val fish = RainbowFishV2("name", 1, 2, 3, sleeping = false, hungry = true, angry = false)
        assertEquals("name", fish.name)
        assertEquals(1, fish.age)
        assertEquals(2, fish.lengthMeters)
        assertEquals(3, fish.weightTons)
        assertFalse(fish.sleeping)
        assertTrue(fish.hungry)
        assertFalse(fish.angry)
    }
}