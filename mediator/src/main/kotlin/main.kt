fun main() {
    PartyImpl().apply {
        addMember(Hobbit().also { it.act(Action.ENEMY) })
        addMember(Wizard().also { it.act(Action.TALE) })
        addMember(Rogue().also { it.act(Action.GOLD) })
        addMember(Hunter().also { it.act(Action.HUNT) })
    }
}