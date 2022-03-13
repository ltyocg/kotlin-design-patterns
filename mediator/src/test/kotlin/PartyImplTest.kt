import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class PartyImplTest {
    @Test
    fun partyAction() {
        val partyMember1 = mock<PartyMember>()
        val partyMember2 = mock<PartyMember>()
        val party = PartyImpl()
        party.addMember(partyMember1)
        party.addMember(partyMember2)
        verify(partyMember1).joinedParty(party)
        verify(partyMember2).joinedParty(party)
        party.act(partyMember1, Action.GOLD)
        verifyNoMoreInteractions(partyMember1)
        verify(partyMember2).partyAction(Action.GOLD)
        verifyNoMoreInteractions(partyMember1, partyMember2)
    }
}
