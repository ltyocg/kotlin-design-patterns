enum class Profession {
    WARRIOR, THIEF, MAGE, PRIEST;

    override fun toString(): String = name.lowercase()
}