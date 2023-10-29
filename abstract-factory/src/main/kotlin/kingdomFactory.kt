sealed interface KingdomFactory {
    fun createCastle(): Castle
    fun createKing(): King
    fun createArmy(): Army

    companion object {
        fun makeFactory(type: KingdomType): KingdomFactory = when (type) {
            KingdomType.ELF -> ElfKingdomFactory
            KingdomType.ORC -> OrcKingdomFactory
        }
    }

    private data object ElfKingdomFactory : KingdomFactory {
        override fun createCastle(): Castle = Castle.ElfCastle()
        override fun createKing(): King = King.ElfKing()
        override fun createArmy(): Army = Army.ElfArmy()
    }

    private data object OrcKingdomFactory : KingdomFactory {
        override fun createCastle(): Castle = Castle.OrcCastle()
        override fun createKing(): King = King.OrcKing()
        override fun createArmy(): Army = Army.OrcArmy()
    }
}

enum class KingdomType {
    ELF, ORC
}
