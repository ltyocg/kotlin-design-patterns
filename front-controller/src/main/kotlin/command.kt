sealed interface Command {
    fun process()
}

data object ArcherCommand : Command {
    override fun process() = ArcherView.display()
}

data object CatapultCommand : Command {
    override fun process() = CatapultView.display()
}

data object UnknownCommand : Command {
    override fun process() = ErrorView.display()
}
