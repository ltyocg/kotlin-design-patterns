class DomainEventProcessor(private val eventJournal: EventJournal) {
    fun process(domainEvent: DomainEvent) {
        domainEvent.process()
        eventJournal.write(domainEvent)
    }

    fun reset() = eventJournal.reset()
    fun recover() = generateSequence { eventJournal.readNext() }.forEach { it.process() }
}
