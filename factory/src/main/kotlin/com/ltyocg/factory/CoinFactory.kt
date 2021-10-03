package com.ltyocg.factory

object CoinFactory {
    fun getCoin(type: CoinType) = type.constructor()
}