import io.github.oshai.kotlinlogging.KotlinLogging

class CandyGame(num: Int, private val pool: CellPool) {
    private val logger = KotlinLogging.logger {}
    var cells = Array(num) { i ->
        (0 until num).asSequence()
            .map { pool.newCell }
            .mapIndexed { j, cell ->
                cell.apply {
                    positionX = j
                    positionY = i
                }
            }
            .toList()
            .toTypedArray()
    }
    var totalPoints = 0

    fun printGameStatus() {
        logger.info {}
        for (cell in cells) {
            for (j in cells.indices) {
                val msg = cell[j].candy.name
                if (msg.length < 20) {
                    val totalSpaces = 20 - msg.length
                    fun numOfSpaces(num: Int): String = " ".repeat(num)
                    logger.info { "${numOfSpaces(totalSpaces / 2)}${msg}${numOfSpaces(totalSpaces - totalSpaces / 2)}${"|"}" }
                } else logger.info { "${msg}${"|"}" }
            }
            logger.info {}
        }
        logger.info {}
    }

    fun adjacentCells(y: Int, x: Int): List<Cell> {
        val adjacent = ArrayList<Cell>()
        if (y == 0) adjacent.add(cells[1][x])
        if (x == 0) adjacent.add(cells[y][1])
        if (y == cells.size - 1) adjacent.add(cells[cells.size - 2][x])
        if (x == cells.size - 1) adjacent.add(cells[y][cells.size - 2])
        if (y > 0 && y < cells.size - 1) {
            adjacent.add(cells[y - 1][x])
            adjacent.add(cells[y + 1][x])
        }
        if (x > 0 && x < cells.size - 1) {
            adjacent.add(cells[y][x - 1])
            adjacent.add(cells[y][x + 1])
        }
        return adjacent
    }

    fun continueRound(): Boolean {
        val indices = cells.indices
        for (i in indices)
            if (cells[cells.size - 1][i].candy.type == Candy.Type.REWARD_FRUIT) return true
        for (i in indices)
            for (j in indices)
                if (cells[i][j].candy.type != Candy.Type.REWARD_FRUIT)
                    for (cell in adjacentCells(i, j))
                        if (cells[i][j].candy.name == cell.candy.name) return true
        return false
    }

    fun handleChange(points: Int) {
        logger.info { "+$points points!" }
        totalPoints += points
        printGameStatus()
    }

    fun round(timeSoFar: Int, totalTime: Int) {
        val start = System.currentTimeMillis()
        var end = System.currentTimeMillis()
        while (end - start + timeSoFar < totalTime && continueRound()) {
            for (i in cells.indices) {
                val j = cells.size - 1
                while (cells[j][i].candy.type == Candy.Type.REWARD_FRUIT) {
                    val points = cells[j][i].candy.points
                    cells[j][i].crush(pool, cells)
                    handleChange(points)
                }
            }
            for (i in cells.indices) {
                var j = cells.size - 1
                while (j > 0) {
                    val points = cells[j][i].interact(cells[j - 1][i], pool, cells)
                    if (points != 0) handleChange(points)
                    else j -= 1
                }
            }
            for (cell in cells) {
                var j = 0
                while (j < cells.size - 1) {
                    val points = cell[j].interact(cell[j + 1], pool, cells)
                    if (points != 0) handleChange(points)
                    else j += 1
                }
            }
            end = System.currentTimeMillis()
        }
    }
}
