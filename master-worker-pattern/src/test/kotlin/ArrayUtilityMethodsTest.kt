import kotlin.test.Test
import kotlin.test.assertTrue

class ArrayUtilityMethodsTest {
    @Test
    fun arraysSame() = assertTrue(
        ArrayUtilityMethods.arraysSame(
            intArrayOf(1, 4, 2, 6),
            intArrayOf(1, 4, 2, 6)
        )
    )

    @Test
    fun matricesSame() = assertTrue(
        ArrayUtilityMethods.matricesSame(
            arrayOf(intArrayOf(1, 4, 2, 6), intArrayOf(5, 8, 6, 7)),
            arrayOf(intArrayOf(1, 4, 2, 6), intArrayOf(5, 8, 6, 7))
        )
    )
}