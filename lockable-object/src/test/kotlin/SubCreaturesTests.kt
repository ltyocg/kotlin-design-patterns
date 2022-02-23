import domain.CreatureStats
import domain.Elf
import domain.Human
import domain.Orc
import kotlin.test.Test
import kotlin.test.assertEquals

class SubCreaturesTests {
    @Test
    fun stats() {
        val elf = Elf("Limbar")
        val orc = Orc("Dargal")
        val human = Human("Jerry")
        assertEquals(CreatureStats.ELF_HEALTH.value, elf.health)
        assertEquals(CreatureStats.ELF_DAMAGE.value, elf.damage)
        assertEquals(CreatureStats.ORC_DAMAGE.value, orc.damage)
        assertEquals(CreatureStats.ORC_HEALTH.value, orc.health)
        assertEquals(CreatureStats.HUMAN_DAMAGE.value, human.damage)
        assertEquals(CreatureStats.HUMAN_HEALTH.value, human.health)
    }
}