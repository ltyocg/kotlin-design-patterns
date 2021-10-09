package com.ltyocg.front.controller

interface Command {
    fun process()
}

class ArcherCommand : Command {
    override fun process() {
        ArcherView().display()
    }
}

class CatapultCommand : Command {
    override fun process() {
        CatapultView().display()
    }
}

class UnknownCommand : Command {
    override fun process() {
        ErrorView().display()
    }
}