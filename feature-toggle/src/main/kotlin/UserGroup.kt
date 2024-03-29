object UserGroup {
    private val freeGroup = mutableListOf<User>()
    private val paidGroup = mutableListOf<User>()
    fun addUserToFreeGroup(user: User) {
        require(user !in paidGroup) { "User already member of paid group." }
        if (user !in freeGroup) freeGroup.add(user)
    }

    fun addUserToPaidGroup(user: User) {
        require(user !in freeGroup) { "User already member of free group." }
        if (user !in paidGroup) paidGroup.add(user)
    }

    fun isPaid(user: User): Boolean = user in paidGroup
}