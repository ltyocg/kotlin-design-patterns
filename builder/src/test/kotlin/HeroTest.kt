import kotlin.test.Test
import kotlin.test.assertEquals

class HeroTest {
    @Test
    fun `test build Hero`() {
        val heroName = "Sir Lancelot"
        val hero = Hero(
            Profession.WARRIOR,
            heroName,
            HairType.LONG_CURLY,
            HairColor.BLOND,
            Armor.CHAIN_MAIL,
            Weapon.SWORD
        )
        assertEquals(Profession.WARRIOR, hero.profession)
        assertEquals(heroName, hero.name)
        assertEquals(Armor.CHAIN_MAIL, hero.armor)
        assertEquals(Weapon.SWORD, hero.weapon)
        assertEquals(HairType.LONG_CURLY, hero.hairType)
        assertEquals(HairColor.BLOND, hero.hairColor)
    }
}