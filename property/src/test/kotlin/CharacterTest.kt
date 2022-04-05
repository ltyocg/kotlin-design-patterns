import kotlin.test.*

class CharacterTest {
    @Test
    fun `prototype stats`() {
        val prototype = Character()
        Stats.values().forEach {
            assertFalse(it in prototype)
            assertNull(prototype[it])
            val expectedValue = it.ordinal
            prototype[it] = expectedValue
            assertTrue(it in prototype)
            assertEquals(expectedValue, prototype[it])
            prototype.remove(it)
            assertFalse(it in prototype)
            assertNull(prototype[it])
        }
    }

    @Test
    fun `character stats`() {
        val prototype = Character()
        Stats.values().forEach { prototype[it] = it.ordinal }
        val mage = Character(Character.Type.MAGE, prototype)
        Stats.values().forEach {
            assertTrue(it in mage)
            assertEquals(it.ordinal, mage[it])
        }
    }

    @Test
    fun `test toString`() {
        val prototype = Character().apply {
            this[Stats.ARMOR] = 1
            this[Stats.AGILITY] = 2
            this[Stats.INTELLECT] = 3
        }
        assertEquals("Stats:\n - AGILITY:2\n - ARMOR:1\n - INTELLECT:3\n", prototype.toString())
        assertEquals(
            "Character type: ROGUE\nStats:\n - AGILITY:2\n - ARMOR:1\n",
            Character(Character.Type.ROGUE, prototype).apply { remove(Stats.INTELLECT) }.toString()
        )
        assertEquals(
            "Player: weak\nStats:\n - AGILITY:2\n - INTELLECT:3\n",
            Character("weak", prototype).apply { remove(Stats.ARMOR) }.toString()
        )
    }

    @Test
    fun name() {
        val prototype = Character().apply {
            this[Stats.ARMOR] = 1
            this[Stats.INTELLECT] = 2
        }
        assertNull(prototype.name)
        assertNull(Character(Character.Type.ROGUE, prototype).apply { remove(Stats.INTELLECT) }.name)
        assertEquals("weak", Character("weak", prototype).apply { remove(Stats.ARMOR) }.name)
    }

    @Test
    fun type() {
        val prototype = Character().apply {
            this[Stats.ARMOR] = 1
            this[Stats.INTELLECT] = 2
        }
        assertNull(prototype.type)
        assertEquals(Character.Type.ROGUE, Character(Character.Type.ROGUE, prototype).apply { remove(Stats.INTELLECT) }.type)
        assertNull(Character("weak", prototype).apply { remove(Stats.ARMOR) }.type)
    }
}