import com.ltyocg.commons.assertLogContains
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

class PartyMemberTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun partyAction(memberSupplier: () -> PartyMember) {
        val member = memberSupplier()
        Action.values().forEach {
            assertLogContains("$member ${it.description}") { member.partyAction(it) }
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun act(memberSupplier: () -> PartyMember) {
        val member = memberSupplier().apply { act(Action.GOLD) }
        val party = mock<Party>()
        assertLogContains("$member joins the party") { member.joinedParty(party) }
        Action.values().forEach {
            assertLogContains("$member $it") { member.act(it) }
            verify(party).act(member, it)
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun testToString(memberSupplier: () -> PartyMember) {
        val member = memberSupplier()
        assertEquals(member.javaClass.simpleName, member.toString())
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Arguments> = listOf(
            Arguments.of({ Hobbit() }),
            Arguments.of({ Hunter() }),
            Arguments.of({ Rogue() }),
            Arguments.of({ Wizard() })
        )
    }
}