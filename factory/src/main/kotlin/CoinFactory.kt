object CoinFactory {
    fun getCoin(type: CoinType) = type.constructor()
}