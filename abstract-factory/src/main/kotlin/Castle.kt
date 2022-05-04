sealed interface Castle {
    val description: String

    class ElfCastle : Castle {
        override val description = "This is the elven castle!"
    }

    class OrcCastle : Castle {
        override val description = "This is the orc castle!"
    }
}