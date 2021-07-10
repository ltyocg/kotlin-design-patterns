package com.ltyocg.abstractfactory

import kotlin.test.Test
import kotlin.test.assertTrue

class AbstractFactoryTest {
    @Test
    fun `verify king creation`() {
        createKingdom(Kingdom.KingdomType.ELF)
        assertTrue(kingdom.king is ElfKing)
        createKingdom(Kingdom.KingdomType.ORC)
        assertTrue(kingdom.king is OrcKing)
    }

    @Test
    fun `verify castle creation`() {
        createKingdom(Kingdom.KingdomType.ELF)
        assertTrue(kingdom.castle is ElfCastle)
        createKingdom(Kingdom.KingdomType.ORC)
        assertTrue(kingdom.castle is OrcCastle)
    }

    @Test
    fun `verify army creation`() {
        createKingdom(Kingdom.KingdomType.ELF)
        assertTrue(kingdom.army is ElfArmy)
        createKingdom(Kingdom.KingdomType.ORC)
        assertTrue(kingdom.army is OrcArmy)
    }

    @Test
    fun `verify elf kingdom creation`() {
        createKingdom(Kingdom.KingdomType.ELF)
        assertTrue(kingdom.king is ElfKing)
        assertTrue(kingdom.castle is ElfCastle)
        assertTrue(kingdom.army is ElfArmy)
    }

    @Test
    fun `verify orc kingdom creation`() {
        createKingdom(Kingdom.KingdomType.ORC)
        assertTrue(kingdom.king is OrcKing)
        assertTrue(kingdom.castle is OrcCastle)
        assertTrue(kingdom.army is OrcArmy)
    }
}