abstract class Input<T>(val data: T) {
    abstract fun divideData(num: Int): List<Input<T>>
}

class ArrayInput(data: Array<IntArray>) : Input<Array<IntArray>>(data) {
    override fun divideData(num: Int): List<Input<Array<IntArray>>> = buildList(num) {
        var rowsDone = 0
        for (rows in data.makeDivisions(num))
            if (rows != 0) {
                val divided = Array(rows) { IntArray(data[0].size) { 0 } }
                System.arraycopy(data, rowsDone, divided, 0, rows)
                rowsDone += rows
                add(ArrayInput(divided))
            } else break
    }
}

private fun Array<IntArray>.makeDivisions(num: Int): IntArray {
    val initialDivision = size / num
    val divisions = IntArray(num) { initialDivision }
    if (initialDivision * num != size) {
        var l = 0
        repeat(size - initialDivision * num) {
            divisions[l] += 1
            l = if (l == num - 1) 0 else l + 1
        }
    }
    return divisions
}