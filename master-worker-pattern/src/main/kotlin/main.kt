import org.slf4j.LoggerFactory
import system.ArrayTransposeMasterWorker

private val log = LoggerFactory.getLogger("main")
fun main() {
    val mw = ArrayTransposeMasterWorker()
    val inputMatrix = ArrayUtilityMethods.createRandomIntMatrix(10, 20)
    val result = mw.getResult(ArrayInput(inputMatrix)) as ArrayResult?
    if (result != null) {
        ArrayUtilityMethods.printMatrix(inputMatrix)
        ArrayUtilityMethods.printMatrix(result.data)
    } else log.info("Please enter non-zero input")
}