interface Coin {
    val description: String
}

class CopperCoin : Coin {
    override val description: String = "This is a copper coin."
}

class GoldCoin : Coin {
    override val description: String = "This is a gold coin."
}