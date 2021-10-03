package com.ltyocg.factory.method

import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

class FactoryMethodTest {
    @Test
    fun `test orc blacksmith with spear`() {
        verifyWeapon(OrcBlacksmith().manufactureWeapon(WeaponType.SPEAR), WeaponType.SPEAR, OrcWeapon::class)
    }

    @Test
    fun `test orc blacksmith with axe`() {
        verifyWeapon(OrcBlacksmith().manufactureWeapon(WeaponType.AXE), WeaponType.AXE, OrcWeapon::class)
    }

    @Test
    fun `test elf blacksmith with short sword`() {
        verifyWeapon(ElfBlacksmith().manufactureWeapon(WeaponType.SHORT_SWORD), WeaponType.SHORT_SWORD, ElfWeapon::class)
    }

    @Test
    fun `test elf blacksmith with spear`() {
        verifyWeapon(ElfBlacksmith().manufactureWeapon(WeaponType.SPEAR), WeaponType.SPEAR, ElfWeapon::class)
    }

    private fun verifyWeapon(weapon: Weapon, expectedWeaponType: WeaponType, kClass: KClass<*>) {
        assertEquals(kClass, weapon::class, "Weapon must be an object of: ${kClass.simpleName}")
        assertEquals(expectedWeaponType, weapon.weaponType, "Weapon must be of weaponType: $expectedWeaponType")
    }
}