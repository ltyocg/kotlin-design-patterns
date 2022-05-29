sealed interface Command {
    fun process()
}

object ArcherCommand : Command {
    override fun process() = ArcherView().display()
}

object CatapultCommand : Command {
    override fun process() = CatapultView().display()
}

object UnknownCommand : Command {
    override fun process() = ErrorView().display()
}