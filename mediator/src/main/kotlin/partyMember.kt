import io.github.oshai.kotlinlogging.KotlinLogging

interface PartyMember {
    fun joinedParty(party: Party)
    fun partyAction(action: Action)
    fun act(action: Action)
}

abstract class PartyMemberBase : PartyMember {
    private val logger = KotlinLogging.logger {}
    protected var party: Party? = null
    override fun joinedParty(party: Party) {
        logger.info { "$this joins the party" }
        this.party = party
    }

    override fun partyAction(action: Action) = logger.info { "$this ${action.description}" }

    override fun act(action: Action) {
        if (party != null) {
            logger.info { "$this $action" }
            party!!.act(this, action)
        }
    }

    abstract override fun toString(): String
}

class Hobbit : PartyMemberBase() {
    override fun toString(): String = "Hobbit"
}

class Hunter : PartyMemberBase() {
    override fun toString(): String = "Hunter"
}

class Rogue : PartyMemberBase() {
    override fun toString(): String = "Rogue"
}

class Wizard : PartyMemberBase() {
    override fun toString(): String = "Wizard"
}
