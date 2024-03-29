import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

sealed class WeaponTest {
    fun testBasicWeaponActions(weapon: Weapon) {
        val enchantment = weapon.enchantment
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

    object HammerTest : WeaponTest() {
        @Test
        fun hammer() = testBasicWeaponActions(spy(Hammer(mock<FlyingEnchantment>())))
    }

    object SwordTest : WeaponTest() {
        @Test
        fun sword() = testBasicWeaponActions(spy(Sword(mock<FlyingEnchantment>())))
    }
}