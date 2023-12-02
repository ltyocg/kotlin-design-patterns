import io.github.oshai.kotlinlogging.KotlinLogging

abstract class RequestHandler(private val next: RequestHandler?) {
    private val logger = KotlinLogging.logger {}
    open fun handleRequest(req: Request) {
        next?.handleRequest(req)
    }

    protected fun printHandling(req: Request) = logger.info { "$this handling request \"$req\"" }
    abstract override fun toString(): String
}

class OrcCommander(handler: RequestHandler? = null) : RequestHandler(handler) {
    override fun handleRequest(req: Request) {
        if (RequestType.DEFEND_CASTLE == req.requestType) {
            printHandling(req)
            req.markHandled()
        } else super.handleRequest(req)
    }

    override fun toString(): String = "Orc commander"
}

class OrcOfficer(handler: RequestHandler? = null) : RequestHandler(handler) {
    override fun handleRequest(req: Request) =
        if (RequestType.TORTURE_PRISONER == req.requestType) {
            printHandling(req)
            req.markHandled()
        } else super.handleRequest(req)

    override fun toString(): String = "Orc officer"
}

class OrcSoldier(handler: RequestHandler? = null) : RequestHandler(handler) {
    override fun handleRequest(req: Request) =
        if (RequestType.COLLECT_TAX == req.requestType) {
            printHandling(req)
            req.markHandled()
        } else super.handleRequest(req)

    override fun toString(): String = "Orc soldier"
}
