import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
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
        log.info(if (round > 1) "Refreshing.." else "Starting game..")
        cg.printGameStatus()
        cg.round((System.currentTimeMillis() - start).toInt(), givenTime)
        pointsWon += cg.totalPoints
        end = System.currentTimeMillis()
    }
    log.info("Game Over")
    if (pointsWon >= toWin) {
        log.info(pointsWon.toString())
        log.info("You win!!")
    } else {
        log.info(pointsWon.toString())
        log.info("Sorry, you lose!")
    }
}