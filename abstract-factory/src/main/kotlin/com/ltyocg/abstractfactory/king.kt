package com.ltyocg.abstractfactory

interface King {
    val description: String
}

class ElfKing : King {
    override val description = "This is the elven king!"
}

class OrcKing : King {
    override val description = "This is the orc king!"
}