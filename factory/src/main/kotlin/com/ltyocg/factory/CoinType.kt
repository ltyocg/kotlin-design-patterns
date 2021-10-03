package com.ltyocg.factory

enum class CoinType(val constructor: () -> Coin) {
    COPPER(::CopperCoin),
    GOLD(::GoldCoin)
}