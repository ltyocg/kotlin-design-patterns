import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class MeteoroidTest : CollisionTest<Meteoroid>() {
    override val testedObject: Meteoroid
        get() = Meteoroid(1, 2, 3, 4)

    @Test
    fun `test constructor`() {
        with(Meteoroid(1, 2, 3, 4)) {
            assertEquals(1, left)
            assertEquals(2, top)
            assertEquals(3, right)
            assertEquals(4, bottom)
            assertFalse(onFire)
            assertFalse(damaged)
            assertEquals("Meteoroid at [1,2,3,4] damaged=false onFire=false", toString())
        }
    }

    @Test
    fun `test collide flaming asteroid`() {
        testCollision(FlamingAsteroid(1, 1, 3, 4), otherDamaged = false, otherOnFire = true, thisDamaged = false, thisOnFire = false)
    }

    @Test
    fun `test collide meteoroid`() {
        testCollision(Meteoroid(1, 1, 3, 4), otherDamaged = false, otherOnFire = false, thisDamaged = false, thisOnFire = false)
    }

    @Test
    fun `test collide space station iss`() {
        testCollision(SpaceStationIss(1, 1, 3, 4), otherDamaged = true, otherOnFire = false, thisDamaged = false, thisOnFire = false)
    }

    @Test
    fun `test collide space station mir`() {
        testCollision(SpaceStationMir(1, 1, 3, 4), otherDamaged = true, otherOnFire = false, thisDamaged = false, thisOnFire = false)
    }
}