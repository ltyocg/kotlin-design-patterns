import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val givenTime = 50
    val toWin = 500
    var pointsWon = 0
    val numOfRows = 3
    val start = System.currentTimeMillis()
    var end = System.currentTimeMillis()
    var round = 0
    while (pointsWon < toWin && end - start < givenTime) {
        round++
        val cg = CandyGame(numOfRows, CellPool(numOfRows * numOfRows + 5))
        logger.info { if (round > 1) "Refreshing.." else "Starting game.." }
        cg.printGameStatus()
        cg.round((System.currentTimeMillis() - start).toInt(), givenTime)
        pointsWon += cg.totalPoints
        end = System.currentTimeMillis()
    }
    logger.info { "Game Over" }
    if (pointsWon >= toWin) {
        logger.info { pointsWon }
        logger.info { "You win!!" }
    } else {
        logger.info { pointsWon }
        logger.info { "Sorry, you lose!" }
    }
}
