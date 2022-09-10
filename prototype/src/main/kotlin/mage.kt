abstract class Mage : Prototype<Mage>()
data class ElfMage(private val helpType: String) : Mage() {
    override fun toString(): String = "Elven mage helps in $helpType"
}

data class OrcMage(private val weapon: String) : Mage() {
    override fun toString(): String = "Orcish mage attacks with $weapon"
}