import java.security.SecureRandom

class CellPool(num: Int) {
    private companion object {
        private val RANDOM = SecureRandom()
        private const val FRUIT = "fruit"
        private const val CANDY = "candy"
    }

    val randomCode = try {
        JsonParser().apply { parse() }.candies.filterKeys { it != FRUIT && it != CANDY }.values.toTypedArray()
    } catch (e: Exception) {
        e.printStackTrace()
        arrayOf(
            Candy("cherry", FRUIT, Candy.Type.REWARD_FRUIT, 20),
            Candy("mango", FRUIT, Candy.Type.REWARD_FRUIT, 20),
            Candy("purple popsicle", CANDY, Candy.Type.CRUSHABLE_CANDY, 10),
            Candy("green jellybean", CANDY, Candy.Type.CRUSHABLE_CANDY, 10),
            Candy("orange gum", CANDY, Candy.Type.CRUSHABLE_CANDY, 10)
        )
    }
    val pool = (0 until num).asSequence()
        .map { RANDOM.nextInt(randomCode.size) }
        .map(randomCode::get)
        .map(::Cell)
        .toMutableList()
    val pointer: Int
        get() = pool.size - 1
    val newCell: Cell
        get() = pool.removeAt(pointer)

    fun addNewCell(c: Cell) {
        pool.add(c.apply { candy = randomCode[RANDOM.nextInt(randomCode.size)] })
    }
}