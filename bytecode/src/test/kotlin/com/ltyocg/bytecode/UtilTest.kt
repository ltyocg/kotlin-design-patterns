package com.ltyocg.bytecode

import kotlin.test.Test
import kotlin.test.assertEquals

class UtilTest {
    @Test
    fun `test empty instruction`() {
        assertEquals(0, convertToByteCode("").size)
    }

    @Test
    fun `test instructions`() {
        val bytecode = convertToByteCode("LITERAL 35 SET_HEALTH SET_WISDOM SET_AGILITY PLAY_SOUND SPAWN_PARTICLES GET_HEALTH ADD DIVIDE")
        assertEquals(10, bytecode.size)
        assertEquals(Instruction.LITERAL.intValue, bytecode[0])
        assertEquals(35, bytecode[1])
        assertEquals(Instruction.SET_HEALTH.intValue, bytecode[2])
        assertEquals(Instruction.SET_WISDOM.intValue, bytecode[3])
        assertEquals(Instruction.SET_AGILITY.intValue, bytecode[4])
        assertEquals(Instruction.PLAY_SOUND.intValue, bytecode[5])
        assertEquals(Instruction.SPAWN_PARTICLES.intValue, bytecode[6])
        assertEquals(Instruction.GET_HEALTH.intValue, bytecode[7])
        assertEquals(Instruction.ADD.intValue, bytecode[8])
        assertEquals(Instruction.DIVIDE.intValue, bytecode[9])
    }
}