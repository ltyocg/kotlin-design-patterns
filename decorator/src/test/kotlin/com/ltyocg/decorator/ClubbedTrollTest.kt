package com.ltyocg.decorator

import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test
import kotlin.test.assertEquals

class ClubbedTrollTest {
    @Test
    fun `test ClubbedTroll`() {
        val simpleTroll = spy(SimpleTroll())
        val clubbed = ClubbedTroll(simpleTroll)
        assertEquals(20, clubbed.attackPower)
        verify(simpleTroll, times(1)).attackPower
        clubbed.attack()
        verify(simpleTroll, times(1)).attack()
        clubbed.fleeBattle()
        verify(simpleTroll, times(1)).fleeBattle()
        verifyNoMoreInteractions(simpleTroll)
    }
}