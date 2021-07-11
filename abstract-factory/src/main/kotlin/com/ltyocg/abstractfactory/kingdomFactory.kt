package com.ltyocg.abstractfactory

interface KingdomFactory {
    fun createCastle(): Castle
    fun createKing(): King
    fun createArmy(): Army
}

class ElfKingdomFactory : KingdomFactory {
    override fun createCastle(): Castle = ElfCastle()
    override fun createKing(): King = ElfKing()
    override fun createArmy(): Army = ElfArmy()
}

class OrcKingdomFactory : KingdomFactory {
    override fun createCastle(): Castle = OrcCastle()
    override fun createKing(): King = OrcKing()
    override fun createArmy(): Army = OrcArmy()
}

enum class KingdomType {
    ELF, ORC
}

fun makeFactory(type: KingdomType): KingdomFactory = when (type) {
    KingdomType.ELF -> ElfKingdomFactory()
    KingdomType.ORC -> OrcKingdomFactory()
}