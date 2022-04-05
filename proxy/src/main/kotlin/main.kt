fun main() = with(WizardTowerProxy(IvoryTower())) {
    enter(Wizard("Red wizard"))
    enter(Wizard("White wizard"))
    enter(Wizard("Black wizard"))
    enter(Wizard("Green wizard"))
    enter(Wizard("Brown wizard"))
}