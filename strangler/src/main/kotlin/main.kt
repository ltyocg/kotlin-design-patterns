fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5)
    OldArithmetic(OldSource).run {
        sum(*nums)
        mul(*nums)
    }
    HalfArithmetic(HalfSource, OldSource).run {
        sum(*nums)
        mul(*nums)
        ifHasZero(*nums)
    }
    NewArithmetic(NewSource).run {
        sum(*nums)
        mul(*nums)
        ifHasZero(*nums)
    }
}