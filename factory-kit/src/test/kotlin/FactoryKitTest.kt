import kotlin.reflect.KClass
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FactoryKitTest {
    private lateinit var factory: WeaponFactory

    @BeforeTest
    fun init() {
        factory = WeaponFactory.factory {
            it.add(WeaponType.AXE, ::Axe)
            it.add(WeaponType.SPEAR, ::Spear)
            it.add(WeaponType.SWORD, ::Sword)
        }
    }

    @Test
    fun `test spear weapon`() {
        verifyWeapon(factory.create(WeaponType.SPEAR), Spear::class)
    }

    @Test
    fun `test axe weapon`() {
        verifyWeapon(factory.create(WeaponType.AXE), Axe::class)
    }

    @Test
    fun `test weapon`() {
        verifyWeapon(factory.create(WeaponType.SWORD), Sword::class)
    }

    private fun verifyWeapon(weapon: Weapon, kClass: KClass<*>) {
        assertEquals(kClass, weapon::class, "Weapon must be an object of: ${kClass.simpleName}")
    }
}