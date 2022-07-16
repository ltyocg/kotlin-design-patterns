object FrontController {
    fun handleRequest(request: String) = try {
        (try {
            Class.forName("${request}Command").kotlin.objectInstance as Command
        } catch (e: ClassNotFoundException) {
            UnknownCommand
        }).process()
    } catch (e: Exception) {
        throw ApplicationException(e)
    }
}