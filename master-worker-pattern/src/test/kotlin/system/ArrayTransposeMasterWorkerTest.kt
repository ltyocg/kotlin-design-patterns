package system

import ArrayInput
import ArrayResult
import ArrayUtilityMethods
import kotlin.test.Test
import kotlin.test.assertTrue

class ArrayTransposeMasterWorkerTest {
    @Test
    fun getResult() = assertTrue(
        ArrayUtilityMethods.matricesSame(
            (ArrayTransposeMasterWorker().getResult(ArrayInput(Array(5) { IntArray(5) { it + 1 } })) as ArrayResult).data,
            Array(5) { i -> IntArray(5) { i + 1 } }
        )
    )
}