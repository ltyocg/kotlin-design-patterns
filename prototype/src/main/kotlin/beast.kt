abstract class Beast : Prototype {
    abstract override fun copy(): Beast
}

data class ElfBeast(private val helpType: String) : Beast() {
    constructor(elfBeast: ElfBeast) : this(elfBeast.helpType)

    override fun copy(): ElfBeast = ElfBeast(this)
    override fun toString(): String = "Elven eagle helps in $helpType"
}

data class OrcBeast(private val weapon: String) : Beast() {
    constructor(orcBeast: OrcBeast) : this(orcBeast.weapon)

    override fun copy(): OrcBeast = OrcBeast(this)
    override fun toString(): String = "Orcish wolf attacks with $weapon"
}