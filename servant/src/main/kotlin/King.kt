class King : Royalty {
    private var isDrunk = false
    private var isHungry = true
    override var mood = false
        private set
    private var complimentReceived = false
    override fun getFed() {
        isHungry = false
    }

    override fun getDrink() {
        isDrunk = true
    }

    override fun receiveCompliments() {
        complimentReceived = true
    }

    override fun changeMood() {
        if (!isHungry && isDrunk) mood = true
        if (complimentReceived) mood = false
    }
}