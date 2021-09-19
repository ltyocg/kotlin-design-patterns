package com.ltyocg.doubledispatch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FlamingAsteroidTest : CollisionTest<FlamingAsteroid>() {
    override val testedObject: FlamingAsteroid
        get() = FlamingAsteroid(1, 2, 3, 4)

    @Test
    fun `test constructor`() {
        with(FlamingAsteroid(1, 2, 3, 4)) {
            assertEquals(1, left)
            assertEquals(2, top)
            assertEquals(3, right)
            assertEquals(4, bottom)
            assertTrue(onFire)
            assertFalse(damaged)
            assertEquals("FlamingAsteroid at [1,2,3,4] damaged=false onFire=true", toString())
        }
    }

    @Test
    fun `test collide flaming asteroid`() {
        testCollision(FlamingAsteroid(1, 2, 3, 4), otherDamaged = false, otherOnFire = true, thisDamaged = false, thisOnFire = true)
    }

    @Test
    fun `test collide meteoroid`() {
        testCollision(Meteoroid(1, 1, 3, 4), otherDamaged = false, otherOnFire = false, thisDamaged = false, thisOnFire = true)
    }

    @Test
    fun `test collide space station iss`() {
        testCollision(SpaceStationIss(1, 1, 3, 4), otherDamaged = true, otherOnFire = true, thisDamaged = false, thisOnFire = true)
    }

    @Test
    fun `test collide space station mir`() {
        testCollision(SpaceStationMir(1, 1, 3, 4), otherDamaged = true, otherOnFire = true, thisDamaged = false, thisOnFire = true)
    }
}