abstract class Warlord : Prototype {
    abstract override fun copy(): Warlord
}

data class ElfWarlord(private val helpType: String) : Warlord() {
    constructor(elfWarlord: ElfWarlord) : this(elfWarlord.helpType)

    override fun copy(): ElfWarlord = ElfWarlord(this)
    override fun toString(): String = "Elven warlord helps in $helpType"
}

data class OrcWarlord(private val weapon: String) : Warlord() {
    constructor(orcWarlord: OrcWarlord) : this(orcWarlord.weapon)

    override fun copy(): OrcWarlord = OrcWarlord(this)
    override fun toString(): String = "Orcish warlord attacks with $weapon"
}