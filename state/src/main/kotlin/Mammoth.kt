class Mammoth {
    private var state: State = PeacefulState(this)
    fun timePasses() = changeStateTo(if (state.javaClass == PeacefulState::class.java) AngryState(this) else PeacefulState(this))
    private fun changeStateTo(newState: State) {
        state = newState
        state.onEnterState()
    }

    override fun toString(): String = "The mammoth"
    fun observe() = state.observe()
}