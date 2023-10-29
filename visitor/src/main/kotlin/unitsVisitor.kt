import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface UnitsVisitor {
    fun visitSoldier(soldier: Soldier) {}
    fun visitSergeant(sergeant: Sergeant) {}
    fun visitCommander(commander: Commander) {}
}

class CommanderVisitor : UnitsVisitor {
    private val logger = KotlinLogging.logger {}
    override fun visitCommander(commander: Commander) = logger.info { "Good to see you $commander" }
}

class SergeantVisitor : UnitsVisitor {
    private val logger = KotlinLogging.logger {}
    override fun visitSergeant(sergeant: Sergeant) = logger.info { "Hello $sergeant" }
}

class SoldierVisitor : UnitsVisitor {
    private val logger = KotlinLogging.logger {}
    override fun visitSoldier(soldier: Soldier) = logger.info { "Greetings $soldier" }
}
