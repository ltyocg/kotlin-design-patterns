abstract class Warlord : Prototype<Warlord>()
data class ElfWarlord(private val helpType: String) : Warlord() {
    override fun toString(): String = "Elven warlord helps in $helpType"
}

data class OrcWarlord(private val weapon: String) : Warlord() {
    override fun toString(): String = "Orcish warlord attacks with $weapon"
}