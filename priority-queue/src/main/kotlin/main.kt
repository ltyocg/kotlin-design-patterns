fun main() {
    val queueManager = QueueManager(10)
    repeat(10) {
        queueManager.publishMessage(Message("Low Message Priority", 0))
    }
    repeat(10) {
        queueManager.publishMessage(Message("High Message Priority", 1))
    }
    Worker(queueManager).run()
}