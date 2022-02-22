fun convertToByteCode(instructions: String): IntArray {
    val content = instructions.trim()
    return if (content.isEmpty()) intArrayOf() else content.split(" ")
        .map {
            try {
                Instruction.valueOf(it).intValue
            } catch (e: IllegalArgumentException) {
                it.toInt()
            }
        }
        .toIntArray()
}