interface Army {
    val description: String
}

class ElfArmy : Army {
    override val description = "This is the elven army!"
}

class OrcArmy : Army {
    override val description = "This is the orc army!"
}