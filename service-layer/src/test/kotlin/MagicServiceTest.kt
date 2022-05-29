import org.mockito.kotlin.*
import spell.Spell
import spell.SpellDao
import spellbook.Spellbook
import spellbook.SpellbookDao
import wizard.Wizard
import wizard.WizardDao
import kotlin.test.Test
import kotlin.test.assertEquals

class MagicServiceTest {
    @Test
    fun findAllWizards() {
        val wizardDao = mock<WizardDao>()
        val spellbookDao = mock<SpellbookDao>()
        val spellDao = mock<SpellDao>()
        val service = MagicService(wizardDao, spellbookDao, spellDao)
        verifyNoInteractions(wizardDao, spellbookDao, spellDao)
        service.findAllWizards()
        verify(wizardDao).findAll()
        verifyNoMoreInteractions(wizardDao, spellbookDao, spellDao)
    }

    @Test
    fun findAllSpellbooks() {
        val wizardDao = mock<WizardDao>()
        val spellbookDao = mock<SpellbookDao>()
        val spellDao = mock<SpellDao>()
        val service = MagicService(wizardDao, spellbookDao, spellDao)
        verifyNoInteractions(wizardDao, spellbookDao, spellDao)
        service.findAllSpellbooks()
        verify(spellbookDao).findAll()
        verifyNoMoreInteractions(wizardDao, spellbookDao, spellDao)
    }

    @Test
    fun findAllSpells() {
        val wizardDao = mock<WizardDao>()
        val spellbookDao = mock<SpellbookDao>()
        val spellDao = mock<SpellDao>()
        val service = MagicService(wizardDao, spellbookDao, spellDao)
        verifyNoInteractions(wizardDao, spellbookDao, spellDao)
        service.findAllSpells()
        verify(spellDao).findAll()
        verifyNoMoreInteractions(wizardDao, spellbookDao, spellDao)
    }

    @Test
    fun findWizardsWithSpellbook() {
        val spellbook = mock<Spellbook>()
        val wizards = mutableSetOf<Wizard>(mock(), mock(), mock())
        whenever(spellbook.wizards).thenReturn(wizards)
        val spellbookDao = mock<SpellbookDao>()
        val bookname = "bookname"
        whenever(spellbookDao.findByName(eq(bookname))).thenReturn(spellbook)
        val wizardDao = mock<WizardDao>()
        val spellDao = mock<SpellDao>()
        val service = MagicService(wizardDao, spellbookDao, spellDao)
        verifyNoInteractions(wizardDao, spellbookDao, spellDao)
        val result = service.findWizardsWithSpellbook(bookname)
        verify(spellbookDao).findByName(eq(bookname));
        verify(spellbook).wizards
        assertEquals(3, result.size)
        verifyNoMoreInteractions(wizardDao, spellbookDao, spellDao)
    }

    @Test
    fun findWizardsWithSpell() {
        val spellbook = mock<Spellbook>()
        val wizards = mutableSetOf<Wizard>(mock(), mock(), mock())
        whenever(spellbook.wizards).thenReturn(wizards)
        val spell = mock<Spell>()
        whenever(spell.spellbook).thenReturn(spellbook)
        val spellDao = mock<SpellDao>()
        val spellName = "spellname"
        whenever(spellDao.findByName(eq(spellName))).thenReturn(spell)
        val wizardDao = mock<WizardDao>()
        val spellbookDao = mock<SpellbookDao>()
        val service = MagicService(wizardDao, spellbookDao, spellDao)
        verifyNoInteractions(wizardDao, spellbookDao, spellDao)
        val result = service.findWizardsWithSpell(spellName)
        verify(spellDao).findByName(eq(spellName));
        verify(spellbook).wizards
        assertEquals(3, result.size)
        verifyNoMoreInteractions(wizardDao, spellbookDao, spellDao)
    }
}