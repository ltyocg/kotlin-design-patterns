import org.slf4j.LoggerFactory

sealed interface UnitsVisitor {
    fun visitSoldier(soldier: Soldier)
    fun visitSergeant(sergeant: Sergeant)
    fun visitCommander(commander: Commander)
}

class CommanderVisitor : UnitsVisitor {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun visitSoldier(soldier: Soldier) {}
    override fun visitSergeant(sergeant: Sergeant) {}
    override fun visitCommander(commander: Commander) = log.info("Good to see you {}", commander)
}

class SergeantVisitor : UnitsVisitor {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun visitSoldier(soldier: Soldier) {}
    override fun visitSergeant(sergeant: Sergeant) = log.info("Hello {}", sergeant)
    override fun visitCommander(commander: Commander) {}
}

class SoldierVisitor : UnitsVisitor {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun visitSoldier(soldier: Soldier) = log.info("Greetings {}", soldier)
    override fun visitSergeant(sergeant: Sergeant) {}
    override fun visitCommander(commander: Commander) {}
}