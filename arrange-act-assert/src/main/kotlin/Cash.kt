class Cash(private var amount: Int) {
    fun plus(addend: Int) {
        amount += addend
    }

    fun minus(subtrahend: Int): Boolean =
        (amount >= subtrahend).also { if (it) amount -= subtrahend }

    fun count(): Int = amount
}