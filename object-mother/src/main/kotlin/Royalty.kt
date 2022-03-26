interface Royalty {
    fun makeDrunk()
    fun makeSober()
    fun makeHappy()
    fun makeUnhappy()
}

class King : Royalty {
    var isDrunk = false
    var isHappy = false
    override fun makeDrunk() {
        isDrunk = true
    }

    override fun makeSober() {
        isDrunk = false
    }

    override fun makeHappy() {
        isHappy = true
    }

    override fun makeUnhappy() {
        isHappy = false
    }

    fun flirt(queen: Queen) =
        if (queen.getFlirted(this)) makeHappy()
        else makeUnhappy()
}

class Queen : Royalty {
    private var isDrunk = false
    private var isHappy = false
    var isFlirty = false

    override fun makeDrunk() {
        isDrunk = true
    }

    override fun makeSober() {
        isDrunk = false
    }

    override fun makeHappy() {
        isHappy = true
    }

    override fun makeUnhappy() {
        isHappy = false
    }

    fun getFlirted(king: King): Boolean = isFlirty && king.isHappy && !king.isDrunk
}