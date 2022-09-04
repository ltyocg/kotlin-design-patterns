import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContentEquals

sealed class VisitorTest<V : UnitsVisitor>(
    private val visitor: V,
    private val commanderResponse: String?,
    private val sergeantResponse: String?,
    private val soldierResponse: String?
) {
    @Test
    fun visitCommander() {
        val assertListAppender = assertListAppender(CommanderVisitor::class)
        visitor.visitCommander(Commander())
        if (commanderResponse != null) assertContentEquals(listOf(commanderResponse), assertListAppender.formattedList())
    }

    @Test
    fun visitSergeant() {
        val assertListAppender = assertListAppender(SergeantVisitor::class)
        visitor.visitSergeant(Sergeant())
        if (sergeantResponse != null) assertContentEquals(listOf(sergeantResponse), assertListAppender.formattedList())
    }

    @Test
    fun visitSoldier() {
        val assertListAppender = assertListAppender(SoldierVisitor::class)
        visitor.visitSoldier(Soldier())
        if (soldierResponse != null) assertContentEquals(listOf(soldierResponse), assertListAppender.formattedList())
    }

    class CommanderVisitorTest : VisitorTest<CommanderVisitor>(
        CommanderVisitor(),
        "Good to see you commander",
        null,
        null
    )

    class SergeantVisitorTest : VisitorTest<SergeantVisitor>(
        SergeantVisitor(),
        null,
        "Hello sergeant",
        null
    )

    class SoldierVisitorTest : VisitorTest<SoldierVisitor>(
        SoldierVisitor(),
        null,
        null,
        "Greetings soldier"
    )
}