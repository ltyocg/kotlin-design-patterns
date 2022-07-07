import CharacterStepBuilder.newBuilder
import kotlin.test.*

class CharacterStepBuilderTest {
    @Test
    fun `build wizard`() {
        val character = newBuilder()
            .name("Merlin")
            .wizardClass("alchemist")
            .withSpell("poison")
            .withAbility("invisibility")
            .withAbility("wisdom")
            .noMoreAbilities()
            .build()
        assertEquals("Merlin", character.name)
        assertEquals("alchemist", character.wizardClass)
        assertEquals("poison", character.spell)
        val abilities = character.abilities
        assertNotNull(abilities)
        assertEquals(2, abilities.size)
        assertContains(abilities, "invisibility")
        assertContains(abilities, "wisdom")
    }

    @Test
    fun `build poor wizard`() {
        val character = newBuilder()
            .name("Merlin")
            .wizardClass("alchemist")
            .noSpell()
            .build()
        assertEquals("Merlin", character.name)
        assertEquals("alchemist", character.wizardClass)
        assertNull(character.spell)
        assertNull(character.abilities)
    }

    @Test
    fun `build weak wizard`() {
        val character = newBuilder()
            .name("Merlin")
            .wizardClass("alchemist")
            .withSpell("poison")
            .noAbilities()
            .build()
        assertEquals("Merlin", character.name)
        assertEquals("alchemist", character.wizardClass)
        assertEquals("poison", character.spell)
        assertNull(character.abilities)
    }

    @Test
    fun `build warrior`() {
        val character = newBuilder()
            .name("Cuauhtemoc")
            .fighterClass("aztec")
            .withWeapon("spear")
            .withAbility("speed")
            .withAbility("strength")
            .noMoreAbilities()
            .build()
        assertEquals("Cuauhtemoc", character.name)
        assertEquals("aztec", character.fighterClass)
        assertEquals("spear", character.weapon)
        val abilities = character.abilities
        assertNotNull(abilities)
        assertEquals(2, abilities.size)
        assertContains(abilities, "speed")
        assertContains(abilities, "strength")
    }

    @Test
    fun `build poor warrior`() {
        val character = newBuilder()
            .name("Poor warrior")
            .fighterClass("none")
            .noWeapon()
            .build()
        assertEquals("Poor warrior", character.name)
        assertEquals("none", character.fighterClass)
        assertNull(character.weapon)
        assertNull(character.abilities)
    }

    @Test
    fun buildWeakWarrior() {
        val character = newBuilder()
            .name("Weak warrior")
            .fighterClass("none")
            .withWeapon("Slingshot")
            .noAbilities()
            .build()
        assertEquals("Weak warrior", character.name)
        assertEquals("none", character.fighterClass)
        assertEquals("Slingshot", character.weapon)
        assertNull(character.abilities)
    }
}