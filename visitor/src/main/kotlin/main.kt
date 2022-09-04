fun main() {
    with(
        Commander(
            Sergeant(Soldier(), Soldier(), Soldier()),
            Sergeant(Soldier(), Soldier(), Soldier())
        )
    ) {
        accept(SoldierVisitor())
        accept(SergeantVisitor())
        accept(CommanderVisitor())
    }
}