package com.ltyocg.command

fun main() {
    val wizard = Wizard()
    val goblin = Goblin()
    goblin.printStatus()
    wizard.castSpell(goblin::changeSize)
    goblin.printStatus()
    wizard.castSpell(goblin::changeVisibility)
    goblin.printStatus()
    wizard.undoLastSpell()
    goblin.printStatus()
    wizard.undoLastSpell()
    goblin.printStatus()
    wizard.redoLastSpell()
    goblin.printStatus()
    wizard.redoLastSpell()
    goblin.printStatus()
}