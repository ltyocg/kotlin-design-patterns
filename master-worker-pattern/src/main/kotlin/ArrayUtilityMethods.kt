import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom

object ArrayUtilityMethods {
    private val logger = KotlinLogging.logger {}
    private val RANDOM = SecureRandom()
    fun createRandomIntMatrix(rows: Int, columns: Int): Array<IntArray> =
        Array(rows) { IntArray(columns) { RANDOM.nextInt(10) } }

    fun printMatrix(matrix: Array<IntArray>) =
        matrix.forEach { ints ->
            ints.forEach { logger.info { "$it " } }
            logger.info {}
        }

    fun matricesSame(m1: Array<IntArray>, m2: Array<IntArray>): Boolean {
        if (m1.size != m2.size) return false
        repeat(m1.size) { if (!arraysSame(m1[it], m2[it])) return false }
        return true
    }

    fun arraysSame(a1: IntArray, a2: IntArray): Boolean {
        if (a1.size != a2.size) return false
        repeat(a1.size) { if (a1[it] != a2[it]) return false }
        return true
    }
}
