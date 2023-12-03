import io.github.oshai.kotlinlogging.KotlinLogging
import system.ArrayTransposeMasterWorker

private val logger = KotlinLogging.logger {}
fun main() {
    val mw = ArrayTransposeMasterWorker()
    val inputMatrix = ArrayUtilityMethods.createRandomIntMatrix(10, 20)
    val result = mw.getResult(ArrayInput(inputMatrix)) as ArrayResult?
    if (result != null) {
        ArrayUtilityMethods.printMatrix(inputMatrix)
        ArrayUtilityMethods.printMatrix(result.data)
    } else logger.info { "Please enter non-zero input" }
}
