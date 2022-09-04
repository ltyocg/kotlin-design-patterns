class Cell(
    var candy: Candy,
    var positionX: Int = 0,
    var positionY: Int = 0
) {

    fun crush(pool: CellPool, cellMatrix: Array<Array<Cell>>) {
        pool.addNewCell(this)
        fillThisSpace(pool, cellMatrix)
    }

    fun fillThisSpace(pool: CellPool, cellMatrix: Array<Array<Cell>>) {
        for (y in positionY downTo 1) {
            cellMatrix[y][positionX] = cellMatrix[y - 1][positionX]
            cellMatrix[y][positionX].positionY = y
        }
        cellMatrix[0][positionX] = pool.newCell
        cellMatrix[0][positionX].positionX = positionX
        cellMatrix[0][positionX].positionY = 0
    }

    fun handleCrush(c: Cell, pool: CellPool, cellMatrix: Array<Array<Cell>>) =
        if (positionY >= c.positionY) {
            crush(pool, cellMatrix)
            c.crush(pool, cellMatrix)
        } else {
            c.crush(pool, cellMatrix)
            crush(pool, cellMatrix)
        }

    fun interact(c: Cell, pool: CellPool, cellMatrix: Array<Array<Cell>>): Int =
        if (candy.type != Candy.Type.REWARD_FRUIT && c.candy.type != Candy.Type.REWARD_FRUIT && candy.name == c.candy.name) {
            val pointsWon = candy.points + c.candy.points
            handleCrush(c, pool, cellMatrix)
            pointsWon
        } else 0
}