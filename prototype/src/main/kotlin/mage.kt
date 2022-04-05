abstract class Mage : Prototype {
    abstract override fun copy(): Mage
}

data class ElfMage(private val helpType: String) : Mage() {
    constructor(elfMage: ElfMage) : this(elfMage.helpType)

    override fun copy(): ElfMage = ElfMage(this)
    override fun toString(): String = "Elven mage helps in $helpType"
}

data class OrcMage(private val weapon: String) : Mage() {
    constructor(orcMage: OrcMage) : this(orcMage.weapon)

    override fun copy(): OrcMage = OrcMage(this)
    override fun toString(): String = "Orcish mage attacks with $weapon"
}