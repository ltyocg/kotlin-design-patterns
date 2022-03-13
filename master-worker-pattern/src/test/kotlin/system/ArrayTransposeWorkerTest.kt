package system

import ArrayInput
import ArrayUtilityMethods
import kotlin.test.Test
import kotlin.test.assertTrue

class ArrayTransposeWorkerTest {
    @Test
    fun executeOperation() = assertTrue(
        ArrayUtilityMethods.matricesSame(
            ArrayTransposeWorker(ArrayTransposeMaster(1), 1).apply {
                receivedData = ArrayInput(arrayOf(intArrayOf(2, 4), intArrayOf(3, 5)))
            }.executeOperation().data,
            arrayOf(intArrayOf(2, 3), intArrayOf(4, 5))
        )
    )
}