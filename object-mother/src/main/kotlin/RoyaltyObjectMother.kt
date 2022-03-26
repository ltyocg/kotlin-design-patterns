object RoyaltyObjectMother {
    fun createSoberUnhappyKing(): King = King()
    fun createDrunkKing(): King = King().apply { makeDrunk() }
    fun createHappyKing(): King = King().apply { makeHappy() }
    fun createHappyDrunkKing(): King = King().apply {
        makeHappy()
        makeDrunk()
    }

    fun createFlirtyQueen(): Queen = Queen().apply { isFlirty = true }
    fun createNotFlirtyQueen(): Queen = Queen()
}