import java.time.LocalDateTime

fun main() {
    with(DataBus) {
        subscribe(StatusMember(1))
        subscribe(StatusMember(2))
        val foo = MessageCollectorMember("Foo")
        val bar = MessageCollectorMember("Bar")
        subscribe(foo)
        publish(StartingData(LocalDateTime.now()))
        publish(MessageData("Only Foo should see this"))
        subscribe(bar)
        publish(MessageData("Foo and Bar should see this"))
        unsubscribe(foo)
        publish(MessageData("Only Bar should see this"))
        publish(StoppingData(LocalDateTime.now()))
    }
}