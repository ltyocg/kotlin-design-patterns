abstract class Beast : Prototype<Beast>()
data class ElfBeast(private val helpType: String) : Beast() {
    override fun toString(): String = "Elven eagle helps in $helpType"
}

data class OrcBeast(private val weapon: String) : Beast() {
    override fun toString(): String = "Orcish wolf attacks with $weapon"
}