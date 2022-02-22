enum class Instruction(val intValue: Int) {
    LITERAL(1),
    SET_HEALTH(2),
    SET_WISDOM(3),
    SET_AGILITY(4),
    PLAY_SOUND(5),
    SPAWN_PARTICLES(6),
    GET_HEALTH(7),
    GET_AGILITY(8),
    GET_WISDOM(9),
    ADD(10),
    DIVIDE(11);

    companion object {
        fun getInstruction(value: Int): Instruction {
            try {
                return values().first { it.intValue == value }
            } catch (e: Exception) {
                throw IllegalArgumentException("Invalid instruction value", e)
            }
        }
    }
}