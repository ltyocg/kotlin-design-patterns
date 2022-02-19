package com.ltyocg.lockableobject

import com.ltyocg.lockableobject.domain.*
import kotlin.test.*

class CreatureTest {
    private lateinit var orc: Creature
    private lateinit var elf: Creature
    private lateinit var sword: Lockable

    @BeforeTest
    fun init() {
        elf = Elf("Elf test")
        orc = Orc("Orc test")
        sword = SwordOfAragorn()
    }

    @Test
    fun base() {
        assertEquals("Elf test", elf.name)
        assertEquals(CreatureType.ELF, elf.type)
        assertFailsWith<IllegalArgumentException> { elf.hit(-10) }
    }

    @Test
    fun hit() {
        elf.hit(CreatureStats.ELF_HEALTH.value / 2)
        assertEquals(CreatureStats.ELF_HEALTH.value / 2, elf.health)
        elf.hit(CreatureStats.ELF_HEALTH.value / 2)
        assertFalse(elf.isAlive)
        assertEquals(0, orc.instruments.size)
        assertTrue(orc.acquire(sword))
        assertEquals(1, orc.instruments.size)
        orc.kill()
        assertEquals(0, orc.instruments.size)
    }

    @Test
    fun fight() {
        killCreature(elf, orc)
        assertTrue(elf.isAlive)
        assertFalse(orc.isAlive)
        assertTrue(elf.health > 0)
        assertTrue(orc.health <= 0)
    }

    @Test
    fun acqusition() {
        assertTrue(elf.acquire(sword))
        assertEquals(elf.name, sword.locker?.name)
        assertTrue(sword in elf.instruments)
        assertFalse(orc.acquire(sword))
        killCreature(orc, elf)
        assertTrue(orc.acquire(sword))
        assertEquals(orc, sword.locker)
    }

    private fun killCreature(source: Creature, target: Creature) {
        while (target.isAlive) source.attack(target)
    }

    @Test
    fun `invalid damage`() {
        assertFailsWith<IllegalArgumentException> { elf.hit(-50) }
    }
}