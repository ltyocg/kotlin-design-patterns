import jakarta.servlet.http.HttpServletRequest

class ClientPropertiesBean(req: HttpServletRequest? = null) {
    var worldNewsInterest = true
    var sportsInterest = true
    var businessInterest = true
    var scienceNewsInterest = true
    var name: String = "DEFAULT_NAME"

    init {
        if (req != null) {
            worldNewsInterest = req.getParameter("world").toBoolean()
            sportsInterest = req.getParameter("sport").toBoolean()
            businessInterest = req.getParameter("bus").toBoolean()
            scienceNewsInterest = req.getParameter("sci").toBoolean()
            req.getParameter("name")?.also { if (it.isNotEmpty()) name = it }
        }
    }
}