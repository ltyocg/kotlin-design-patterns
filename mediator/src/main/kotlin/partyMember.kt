import org.slf4j.LoggerFactory

interface PartyMember {
    fun joinedParty(party: Party)
    fun partyAction(action: Action)
    fun act(action: Action)
}

abstract class PartyMemberBase : PartyMember {
    private val log = LoggerFactory.getLogger(javaClass)
    protected var party: Party? = null
    override fun joinedParty(party: Party) {
        log.info("{} joins the party", this)
        this.party = party
    }

    override fun partyAction(action: Action) {
        log.info("{} {}", this, action.description)
    }

    override fun act(action: Action) {
        if (party != null) {
            log.info("{} {}", this, action)
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