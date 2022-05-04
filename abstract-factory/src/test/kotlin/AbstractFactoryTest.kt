import kotlin.test.Test
import kotlin.test.assertTrue

class AbstractFactoryTest {
    @Test
    fun `verify king creation`() {
        assertTrue(createKingdom(KingdomType.ELF).king is King.ElfKing)
        assertTrue(createKingdom(KingdomType.ORC).king is King.OrcKing)
    }

    @Test
    fun `verify castle creation`() {
        assertTrue(createKingdom(KingdomType.ELF).castle is Castle.ElfCastle)
        assertTrue(createKingdom(KingdomType.ORC).castle is Castle.OrcCastle)
    }

    @Test
    fun `verify army creation`() {
        assertTrue(createKingdom(KingdomType.ELF).army is Army.ElfArmy)
        assertTrue(createKingdom(KingdomType.ORC).army is Army.OrcArmy)
    }

    @Test
    fun `verify elf kingdom creation`() = with(createKingdom(KingdomType.ELF)) {
        assertTrue(king is King.ElfKing)
        assertTrue(castle is Castle.ElfCastle)
        assertTrue(army is Army.ElfArmy)
    }

    @Test
    fun `verify orc kingdom creation`() = with(createKingdom(KingdomType.ORC)) {
        assertTrue(king is King.OrcKing)
        assertTrue(castle is Castle.OrcCastle)
        assertTrue(army is Army.OrcArmy)
    }
}