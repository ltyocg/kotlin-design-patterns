interface Message {
    enum class Headers {
        DATE, SENDER
    }

    fun addHeader(header: Headers, value: String)
    fun getHeader(header: Headers): String?
    val headers: Map<Headers, String>
    var body: String?

    companion object {
        val POISON_PILL: Message = object : Message {
            override fun addHeader(header: Headers, value: String) = throw poison()
            override fun getHeader(header: Headers): String = throw poison()
            override val headers: Map<Headers, String>
                get() = throw poison()
            override var body: String?
                get() = throw poison()
                set(_) = throw poison()

            private fun poison(): RuntimeException = UnsupportedOperationException("Poison")
        }
    }
}

class SimpleMessage : Message {
    override val headers = mutableMapOf<Message.Headers, String>()
    override var body: String? = null
    override fun addHeader(header: Message.Headers, value: String) {
        headers[header] = value
    }

    override fun getHeader(header: Message.Headers): String? = headers[header]
}