class Character : Prototype {
    enum class Type {
        WARRIOR, MAGE, ROGUE
    }

    private val prototype: Prototype
    private val properties = mutableMapOf<Stats, Int>()
    var name: String? = null
        private set
    var type: Type? = null
        private set

    constructor() : this(null, object : Prototype {
        override fun get(stat: Stats): Int? = null
        override fun contains(stat: Stats): Boolean = false
        override fun set(stat: Stats, `val`: Int) {}
        override fun remove(stat: Stats) {}
    })

    constructor(type: Type?, prototype: Prototype) {
        this.type = type
        this.prototype = prototype
    }

    constructor(name: String?, prototype: Character) {
        this.name = name
        type = prototype.type
        this.prototype = prototype
    }

    override fun get(stat: Stats): Int? = if (stat in properties) properties[stat] else prototype[stat]
    override fun contains(stat: Stats): Boolean = this[stat] != null
    override fun set(stat: Stats, `val`: Int) {
        properties[stat] = `val`
    }

    override fun remove(stat: Stats) {
        properties.remove(stat)
    }

    override fun toString(): String = buildString {
        if (name != null) {
            append("Player: ")
            append(name)
            append('\n')
        }
        if (type != null) {
            append("Character type: ")
            append(type!!.name)
            append('\n')
        }
        append("Stats:\n")
        Stats.values().asSequence()
            .map { it.name to this@Character[it] }
            .filter { it.second != null }
            .forEach {
                append(" - ")
                append(it.first)
                append(':')
                append(it.second)
                append('\n')
            }
    }
}