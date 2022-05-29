import spellbook.Spellbook
import spellbook.SpellbookDao
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SpellbookDaoTest : BaseDaoTest<Spellbook, SpellbookDao>(::Spellbook, SpellbookDao()) {
    @Test
    fun findByName() = dao.findAll().forEach {
        val spellByName = dao.findByName(it.name)
        assertNotNull(spellByName)
        assertEquals(it.id, spellByName.id)
        assertEquals(it.name, spellByName.name)
    }
}