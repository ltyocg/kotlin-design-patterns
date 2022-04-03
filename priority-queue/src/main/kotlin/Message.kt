class Message(
    private val message: String,
    private val priority: Int
) : Comparable<Message> {
    override fun compareTo(other: Message): Int = priority - other.priority
    override fun toString(): String = "Message{message='$message', priority=$priority}"
}