sealed interface Royalty {
    fun getFed()
    fun getDrink()
    fun changeMood()
    fun receiveCompliments()
    val mood: Boolean

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

    class Queen : Royalty {
        private var isDrunk = true
        private var isHungry = false
        override var mood = false
            private set
        private var isFlirty = true
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
            if (complimentReceived && isFlirty && isDrunk && !isHungry) mood = true
        }

        fun setFlirtiness(f: Boolean) {
            isFlirty = f
        }
    }
}