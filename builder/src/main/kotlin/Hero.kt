data class Hero(
    val profession: Profession,
    val name: String,
    val hairType: HairType? = null,
    val hairColor: HairColor? = null,
    val armor: Armor? = null,
    val weapon: Weapon? = null
) {
    override fun toString(): String = buildString {
        append("This is a ")
        append(profession)
        append(" named ")
        append(name)
        if (hairColor != null || hairType != null) {
            append(" with ")
            if (hairColor != null) {
                append(hairColor)
                append(" ")
            }
            if (hairType != null) {
                append(hairType)
                append(" ")
            }
            append(if (hairType != HairType.BALD) "hair" else "head")
        }
        if (armor != null) {
            append(" wearing ")
            append(armor)
        }
        if (weapon != null) {
            append(" and wielding a ")
            append(weapon)
        }
        append(".")
    }
}
