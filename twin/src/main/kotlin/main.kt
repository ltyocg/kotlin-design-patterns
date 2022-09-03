fun main() {
    val ballItem = BallItem()
    val ballThread = BallThread()
    ballItem.twin = ballThread
    ballThread.twin = ballItem
    ballThread.start()
    waiting()
    ballItem.click()
    waiting()
    ballItem.click()
    waiting()
    ballThread.stopMe()
}

private fun waiting() = Thread.sleep(750)