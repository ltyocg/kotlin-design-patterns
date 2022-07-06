import org.slf4j.LoggerFactory

interface WizardTower {
    fun enter(wizard: Wizard)
}

object IvoryTower : WizardTower {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun enter(wizard: Wizard) = log.info("{} enters the tower.", wizard)
}

class WizardTowerProxy(private val tower: WizardTower) : WizardTower {
    private val log = LoggerFactory.getLogger(javaClass)
    private var numWizards = 0
    override fun enter(wizard: Wizard) {
        if (numWizards < 3) {
            tower.enter(wizard)
            numWizards++
        } else log.info("{} is not allowed to enter!", wizard)
    }
}