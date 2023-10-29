class LayerA {
    val context = ServiceContextFactory.createContext()
    fun addAccountInfo(accountService: String?) {
        context.accountService = accountService
    }
}

class LayerB(layerA: LayerA) {
    val context = layerA.context
    fun addSessionInfo(sessionService: String?) {
        context.sessionService = sessionService
    }
}

class LayerC(layerB: LayerB) {
    val context = layerB.context
    fun addSearchInfo(searchService: String?) {
        context.searchService = searchService
    }
}
