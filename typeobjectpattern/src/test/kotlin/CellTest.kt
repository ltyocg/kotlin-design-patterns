import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CellTest {
    @Test
    fun interact() {
        val c1 = Candy("green jelly", "jelly", Candy.Type.CRUSHABLE_CANDY, 5)
        val c2 = Candy("green apple", "apple", Candy.Type.REWARD_FRUIT, 10)
        val matrix = arrayOf(
            arrayOf(
                Cell(c1, 0, 0),
                Cell(c1, 1, 0),
                Cell(c2, 2, 0),
                Cell(c1, 3, 0)
            )
        )
        val cp = CellPool(5)
        val points1 = matrix[0][0].interact(matrix[0][1], cp, matrix)
        val points2 = matrix[0][2].interact(matrix[0][3], cp, matrix)
        assertTrue(points1 > 0 && points2 == 0)
    }

    @Test
    fun crush() {
        val c1 = Candy("green jelly", "jelly", Candy.Type.CRUSHABLE_CANDY, 5)
        val c2 = Candy("purple candy", "candy", Candy.Type.CRUSHABLE_CANDY, 5)
        val matrix = arrayOf(
            arrayOf(Cell(c1, 0, 0)),
            arrayOf(Cell(c2, 0, 1))
        )
        matrix[0][0] = Cell(c1, 0, 0)
        matrix[1][0] = Cell(c2, 0, 1)
        matrix[1][0].crush(CellPool(5), matrix)
        assertEquals("green jelly", matrix[1][0].candy.name)
    }
}