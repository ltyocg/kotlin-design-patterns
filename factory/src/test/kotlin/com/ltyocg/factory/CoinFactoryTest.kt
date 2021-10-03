package com.ltyocg.factory

import kotlin.test.Test
import kotlin.test.assertTrue

class CoinFactoryTest {
    @Test
    fun `should return gold coin instance`() {
        assertTrue(CoinFactory.getCoin(CoinType.GOLD) is GoldCoin)
    }
}