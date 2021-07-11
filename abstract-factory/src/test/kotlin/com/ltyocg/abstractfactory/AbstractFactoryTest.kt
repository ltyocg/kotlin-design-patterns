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
        createKingdom(KingdomType.ELF).also {
            assertTrue(it.king is ElfKing)
            assertTrue(it.castle is ElfCastle)
            assertTrue(it.army is ElfArmy)
        }
    }

    @Test
    fun `verify orc kingdom creation`() {
        createKingdom(KingdomType.ORC).also {
            assertTrue(it.king is OrcKing)
            assertTrue(it.castle is OrcCastle)
            assertTrue(it.army is OrcArmy)
        }
    }
}