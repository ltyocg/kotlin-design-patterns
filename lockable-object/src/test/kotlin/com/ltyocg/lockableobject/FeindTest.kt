package com.ltyocg.lockableobject

import com.ltyocg.lockableobject.domain.Creature
import com.ltyocg.lockableobject.domain.Elf
import com.ltyocg.lockableobject.domain.Feind
import com.ltyocg.lockableobject.domain.Orc
import kotlin.test.*

class FeindTest {
    private lateinit var orc: Creature
    private lateinit var elf: Creature
    private lateinit var sword: Lockable

    @BeforeTest
    fun init() {
        elf = Elf("Nagdil")
        orc = Orc("Ghandar")
        sword = SwordOfAragorn()
    }

    @Test
    fun `base case`() {
        val base = Thread(Feind(orc, sword))
        assertNull(sword.locker)
        base.start()
        base.join()
        assertEquals(orc, sword.locker)
        val extend = Thread(Feind(elf, sword))
        extend.start()
        extend.join()
        assertTrue(sword.isLocked)
        sword.unlock(if (elf.isAlive) elf else orc)
        assertNull(sword.locker)
    }
}