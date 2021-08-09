package com.ltyocg.bridge

import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test
import kotlin.test.assertNotNull

class HammerTest : WeaponTest() {
    @Test
    fun `test hammer`() {
        testBasicWeaponActions(spy(Hammer(mock<FlyingEnchantment>())))
    }
}

class SwordTest : WeaponTest() {
    @Test
    fun `test sword`() {
        testBasicWeaponActions(spy(Sword(mock<FlyingEnchantment>())))
    }
}

abstract class WeaponTest {
    fun testBasicWeaponActions(weapon: Weapon) {
        assertNotNull(weapon)
        val enchantment = weapon.enchantment
        assertNotNull(enchantment)
        assertNotNull(weapon.enchantment)
        weapon.swing()
        verify(enchantment).apply()
        verifyNoMoreInteractions(enchantment)
        weapon.wield()
        verify(enchantment).onActivate()
        verifyNoMoreInteractions(enchantment)
        weapon.unwield()
        verify(enchantment).onDeactivate()
        verifyNoMoreInteractions(enchantment)
    }
}