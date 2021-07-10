package com.ltyocg.abstractfactory

class Kingdom {
    lateinit var king: King
    lateinit var castle: Castle
    lateinit var army: Army

    companion object {
        fun makeFactory(type: KingdomType): KingdomFactory = when (type) {
            KingdomType.ELF -> ElfKingdomFactory()
            KingdomType.ORC -> OrcKingdomFactory()
        }
    }

    enum class KingdomType {
        ELF, ORC
    }
}