object DataBus {
    private val listeners = mutableSetOf<Member>()
    fun subscribe(member: Member) {
        listeners.add(member)
    }

    fun unsubscribe(member: Member) {
        listeners.remove(member)
    }

    fun publish(event: DataType) {
        event.dataBus = this
        listeners.forEach { it(event) }
    }
}