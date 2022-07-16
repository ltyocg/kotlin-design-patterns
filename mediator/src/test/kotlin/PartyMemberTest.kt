import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
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
        val assertListAppender = assertListAppender(PartyMemberBase::class)
        Action.values().forEach {
            assertListAppender.clear()
            member.partyAction(it)
            assertEquals("$member ${it.description}", assertListAppender.lastMessage())
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun act(memberSupplier: () -> PartyMember) {
        val member = memberSupplier().apply { act(Action.GOLD) }
        val party = mock<Party>()
        val assertListAppender = assertListAppender(PartyMemberBase::class)
        member.joinedParty(party)
        assertEquals("$member joins the party", assertListAppender.lastMessage())
        Action.values().forEach {
            assertListAppender.clear()
            member.act(it)
            assertEquals("$member $it", assertListAppender.lastMessage())
            verify(party).act(member, it)
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `test toString`(memberSupplier: () -> PartyMember) {
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