import spell.Spell
import spell.SpellDao
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SpellDaoTest : BaseDaoTest<Spell, SpellDao>(::Spell, SpellDao()) {
    @Test
    fun findByName() = dao.findAll().forEach {
        val spellByName = dao.findByName(it.name)
        assertNotNull(spellByName)
        assertEquals(it.id, spellByName.id)
        assertEquals(it.name, spellByName.name)
    }
}