interface Party {
    fun addMember(member: PartyMember)
    fun act(actor: PartyMember, action: Action)
}

class PartyImpl : Party {
    private val members = mutableListOf<PartyMember>()
    override fun act(actor: PartyMember, action: Action) = members.asSequence()
        .filter { it != actor }
        .forEach { it.partyAction(action) }

    override fun addMember(member: PartyMember) {
        members.add(member.also { it.joinedParty(this) })
    }
}