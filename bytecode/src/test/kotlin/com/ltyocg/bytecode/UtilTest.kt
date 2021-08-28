package com.ltyocg.bytecode

import kotlin.test.Test
import kotlin.test.assertContentEquals
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
        assertContentEquals(
            intArrayOf(
                Instruction.LITERAL.intValue,
                35,
                Instruction.SET_HEALTH.intValue,
                Instruction.SET_WISDOM.intValue,
                Instruction.SET_AGILITY.intValue,
                Instruction.PLAY_SOUND.intValue,
                Instruction.SPAWN_PARTICLES.intValue,
                Instruction.GET_HEALTH.intValue,
                Instruction.ADD.intValue,
                Instruction.DIVIDE.intValue
            ), bytecode
        )
    }
}