object FrontController {
    fun handleRequest(request: String) {
        try {
            (try {
                Class.forName("com.ltyocg.front.controller.${request}Command")
            } catch (e: ClassNotFoundException) {
                UnknownCommand::class.java
            }.getDeclaredConstructor().newInstance() as Command).process()
        } catch (e: Exception) {
            throw ApplicationException(e)
        }
    }
}