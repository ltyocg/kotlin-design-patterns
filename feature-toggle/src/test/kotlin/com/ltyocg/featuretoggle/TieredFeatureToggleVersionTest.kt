package com.ltyocg.featuretoggle

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TieredFeatureToggleVersionTest {
    private val paidUser = User("Jamie Coder")
    private val freeUser = User("Alan Defect")
    private val service = TieredFeatureToggleVersion()

    @BeforeTest
    fun setUp() {
        UserGroup.addUserToPaidGroup(paidUser)
        UserGroup.addUserToFreeGroup(freeUser)
    }

    @Test
    fun `test getWelcomeMessage for paid user`() {
        assertEquals("You're amazing Jamie Coder. Thanks for paying for this awesome software.", service.getWelcomeMessage(paidUser))
    }

    @Test
    fun `test getWelcomeMessage for free user`() {
        assertEquals("I suppose you can use this software.", service.getWelcomeMessage(freeUser))
    }

    @Test
    fun `test enhanced always true as tiered`() {
        assertTrue(service.enhanced)
    }
}