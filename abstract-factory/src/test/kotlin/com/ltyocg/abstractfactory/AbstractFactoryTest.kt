package com.ltyocg.abstractfactory

import kotlin.test.Test
import kotlin.test.assertTrue

class AbstractFactoryTest {
    @Test
    fun `verify king creation`() {
        assertTrue(createKingdom(KingdomType.ELF).king is ElfKing)
        assertTrue(createKingdom(KingdomType.ORC).king is OrcKing)
    }

    @Test
    fun `verify castle creation`() {
        assertTrue(createKingdom(KingdomType.ELF).castle is ElfCastle)
        assertTrue(createKingdom(KingdomType.ORC).castle is OrcCastle)
    }

    @Test
    fun `verify army creation`() {
        assertTrue(createKingdom(KingdomType.ELF).army is ElfArmy)
        assertTrue(createKingdom(KingdomType.ORC).army is OrcArmy)
    }

    @Test
    fun `verify elf kingdom creation`() {
        with(createKingdom(KingdomType.ELF)) {
            assertTrue(king is ElfKing)
            assertTrue(castle is ElfCastle)
            assertTrue(army is ElfArmy)
        }
    }

    @Test
    fun `verify orc kingdom creation`() {
        with(createKingdom(KingdomType.ORC)) {
            assertTrue(king is OrcKing)
            assertTrue(castle is OrcCastle)
            assertTrue(army is OrcArmy)
        }
    }
}