import wizard.Wizard
import wizard.WizardDao
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class WizardDaoTest : BaseDaoTest<Wizard, WizardDao>(::Wizard, WizardDao()) {
    @Test
    fun findByName() = dao.findAll().forEach {
        val byName = dao.findByName(it.name)
        assertNotNull(byName)
        assertEquals(it.id, byName.id)
        assertEquals(it.name, byName.name)
    }
}