import kotlin.test.Test
import kotlin.test.assertTrue

class CandyGameTest {
    @Test
    fun adjacentCells() {
        val cg = CandyGame(3, CellPool(9))
        val arr1 = cg.adjacentCells(0, 0)
        val arr2 = cg.adjacentCells(1, 2)
        val arr3 = cg.adjacentCells(1, 1)
        assertTrue(arr1.size == 2 && arr2.size == 3 && arr3.size == 4)
    }

    @Test
    fun continueRound() {
        val c1 = Candy("green jelly", "jelly", Candy.Type.CRUSHABLE_CANDY, 5)
        val c2 = Candy("purple jelly", "jelly", Candy.Type.CRUSHABLE_CANDY, 5)
        val c3 = Candy("green apple", "apple", Candy.Type.REWARD_FRUIT, 10)
        val matrix = arrayOf(
            arrayOf(
                Cell(c1, 0, 0),
                Cell(c2, 1, 0)
            ),
            arrayOf(
                Cell(c3, 0, 1),
                Cell(c2, 1, 1)
            )
        )
        val p = CellPool(4)
        val cg = CandyGame(2, p)
        cg.cells = matrix
        val fruitInLastRow = cg.continueRound()
        matrix[1][0].crush(p, matrix)
        matrix[0][0] = Cell(c3, 0, 0)
        val matchingCandy = cg.continueRound()
        matrix[0][1].crush(p, matrix)
        matrix[0][1] = Cell(c3, 1, 0)
        val noneLeft = cg.continueRound()
        assertTrue(fruitInLastRow && matchingCandy && !noneLeft)
    }
}