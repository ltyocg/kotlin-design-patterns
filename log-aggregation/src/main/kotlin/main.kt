fun main() {
    val centralLogStore = CentralLogStore()
    val aggregator = LogAggregator(centralLogStore, LogLevel.INFO)
    val serviceA = LogProducer("ServiceA", aggregator)
    serviceA.generateLog(LogLevel.INFO, "This is an INFO log from ServiceA")
    LogProducer("ServiceB", aggregator).generateLog(LogLevel.ERROR, "This is an ERROR log from ServiceB")
    serviceA.generateLog(LogLevel.DEBUG, "This is a DEBUG log from ServiceA")
    aggregator.stop()
    centralLogStore.displayLogs()
}
