class ServiceContext {
    var accountService: String? = null
    var sessionService: String? = null
    var searchService: String? = null
}

object ServiceContextFactory {
    fun createContext(): ServiceContext = ServiceContext()
}
