import org.slf4j.LoggerFactory

sealed interface UnitsVisitor {
    fun visitSoldier(soldier: Soldier) {}
    fun visitSergeant(sergeant: Sergeant) {}
    fun visitCommander(commander: Commander) {}
}

class CommanderVisitor : UnitsVisitor {
    private companion object {
        private val log = LoggerFactory.getLogger(CommanderVisitor::class.java)
    }

    override fun visitCommander(commander: Commander) = log.info("Good to see you {}", commander)
}

class SergeantVisitor : UnitsVisitor {
    private companion object {
        private val log = LoggerFactory.getLogger(SergeantVisitor::class.java)
    }

    override fun visitSergeant(sergeant: Sergeant) = log.info("Hello {}", sergeant)
}

class SoldierVisitor : UnitsVisitor {
    private companion object {
        private val log = LoggerFactory.getLogger(SoldierVisitor::class.java)
    }

    override fun visitSoldier(soldier: Soldier) = log.info("Greetings {}", soldier)
}