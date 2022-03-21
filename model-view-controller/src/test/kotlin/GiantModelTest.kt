import kotlin.test.Test
import kotlin.test.assertEquals

class GiantModelTest {
    @Test
    fun `set health`() {
        val model = GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED)
        assertEquals(Health.HEALTHY, model.health)
        Health.values().forEach {
            model.health = it
            assertEquals(it, model.health)
            assertEquals("The giant looks $it, alert and saturated.", model.toString())
        }
    }

    @Test
    fun `set fatigue`() {
        val model = GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED)
        assertEquals(Fatigue.ALERT, model.fatigue)
        Fatigue.values().forEach {
            model.fatigue = it
            assertEquals(it, model.fatigue)
            assertEquals("The giant looks healthy, $it and saturated.", model.toString())
        }
    }

    @Test
    fun `set nourishment`() {
        val model = GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED)
        assertEquals(Nourishment.SATURATED, model.nourishment)
        Nourishment.values().forEach {
            model.nourishment = it
            assertEquals(it, model.nourishment)
            assertEquals("The giant looks healthy, alert and $it.", model.toString())
        }
    }
}