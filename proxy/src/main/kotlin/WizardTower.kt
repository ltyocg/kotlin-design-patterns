import io.github.oshai.kotlinlogging.KotlinLogging

interface WizardTower {
    fun enter(wizard: Wizard)
}

object IvoryTower : WizardTower {
    private val logger = KotlinLogging.logger {}
    override fun enter(wizard: Wizard) = logger.info { "$wizard enters the tower." }
}

class WizardTowerProxy(private val tower: WizardTower) : WizardTower {
    private val logger = KotlinLogging.logger {}
    private var numWizards = 0
    override fun enter(wizard: Wizard) {
        if (numWizards < 3) {
            tower.enter(wizard)
            numWizards++
        } else logger.info { "$wizard is not allowed to enter!" }
    }
}
