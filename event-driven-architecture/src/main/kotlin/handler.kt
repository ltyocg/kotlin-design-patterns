import io.github.oshai.kotlinlogging.KotlinLogging

interface Handler<E : Event> {
    fun onEvent(event: E)
}

class UserCreatedEventHandler : Handler<UserCreatedEvent> {
    private val logger = KotlinLogging.logger {}
    override fun onEvent(event: UserCreatedEvent) = logger.info { "User '${event.user.username}' has been Created!" }
}

class UserUpdatedEventHandler : Handler<UserUpdatedEvent> {
    private val logger = KotlinLogging.logger {}
    override fun onEvent(event: UserUpdatedEvent) = logger.info { "User '${event.user.username}' has been Updated!" }
}
