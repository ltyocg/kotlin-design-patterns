class Character(var name: String?) {
    var fighterClass: String? = null
    var wizardClass: String? = null
    var weapon: String? = null
    var spell: String? = null
    var abilities: List<String>? = null
    override fun toString(): String = buildString {
        append("This is a ")
        append(if (fighterClass != null) fighterClass else wizardClass)
        append(" named ")
        append(name)
        append(" armed with a ")
        append(
            when {
                weapon != null -> weapon
                spell != null -> spell
                else -> "with nothing"
            }
        )
        if (abilities != null) append(" and wielding $abilities abilities")
        append('.')
    }
}