package com.ltyocg.doubledispatch

import kotlin.test.assertEquals

abstract class CollisionTest<O : GameObject> {
    abstract val testedObject: O
    fun testCollision(other: GameObject, otherDamaged: Boolean, otherOnFire: Boolean, thisDamaged: Boolean, thisOnFire: Boolean) {
        val tested = testedObject
        tested.collision(other)
        testOnFire(other, tested, otherOnFire)
        testDamaged(other, tested, otherDamaged)
        testOnFire(tested, other, thisOnFire)
        testDamaged(tested, other, thisDamaged)
    }

    private fun testOnFire(target: GameObject, other: GameObject, expectTargetOnFire: Boolean) {
        val targetName = target::class.simpleName
        val otherName = other::class.simpleName
        assertEquals(
            expectTargetOnFire,
            target.onFire,
            if (expectTargetOnFire) "Expected [$targetName] to be on fire after colliding with [$otherName] but it was not!"
            else "Expected [$targetName] not to be on fire after colliding with [$otherName] but it was!"
        )
    }

    private fun testDamaged(target: GameObject, other: GameObject, expectedDamage: Boolean) {
        val targetName = target::class.simpleName
        val otherName = other::class.simpleName
        assertEquals(
            expectedDamage,
            target.damaged,
            if (expectedDamage) "Expected [$targetName] to be damaged after colliding with [$otherName] but it was not!"
            else "Expected [$targetName] not to be damaged after colliding with [$otherName] but it was!"
        )
    }
}