import kotlin.test.Test
import kotlin.test.assertTrue

class ArrayInputTest {
    @Test
    fun divideData() {
        val inputMatrix = ArrayUtilityMethods.createRandomIntMatrix(10, 10)
        val table = ArrayInput(inputMatrix).divideData(4)
        assertTrue(ArrayUtilityMethods.matricesSame(table[0].data, arrayOf(inputMatrix[0], inputMatrix[1], inputMatrix[2])))
        assertTrue(ArrayUtilityMethods.matricesSame(table[1].data, arrayOf(inputMatrix[3], inputMatrix[4], inputMatrix[5])))
        assertTrue(ArrayUtilityMethods.matricesSame(table[2].data, arrayOf(inputMatrix[6], inputMatrix[7])))
        assertTrue(ArrayUtilityMethods.matricesSame(table[3].data, arrayOf(inputMatrix[8], inputMatrix[9])))
    }
}