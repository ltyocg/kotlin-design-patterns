import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DwarvenGoldmineFacadeTest {
    @Test
    fun `full work day`() {
        val goldMine = DwarvenGoldmineFacade()
        val assertListAppender = assertListAppender {
            bind<DwarvenMineWorker>(true)
        }
        goldMine.startNewDay()
        assertContentEquals(
            listOf(
                "Dwarf gold digger wakes up.",
                "Dwarf gold digger goes to the mine.",
                "Dwarf cart operator wakes up.",
                "Dwarf cart operator goes to the mine.",
                "Dwarven tunnel digger wakes up.",
                "Dwarven tunnel digger goes to the mine."
            ),
            assertListAppender.formattedList()
        )
        assertListAppender.clear()
        goldMine.digOutGold()
        assertContentEquals(
            listOf(
                "Dwarf gold digger digs for gold.",
                "Dwarf cart operator moves gold chunks out of the mine.",
                "Dwarven tunnel digger creates another promising tunnel."
            ),
            assertListAppender.formattedList()
        )
        assertListAppender.clear()
        goldMine.endDay()
        assertContentEquals(
            listOf(
                "Dwarf gold digger goes home.",
                "Dwarf gold digger goes to sleep.",
                "Dwarf cart operator goes home.",
                "Dwarf cart operator goes to sleep.",
                "Dwarven tunnel digger goes home.",
                "Dwarven tunnel digger goes to sleep."
            ),
            assertListAppender.formattedList()
        )
    }
}