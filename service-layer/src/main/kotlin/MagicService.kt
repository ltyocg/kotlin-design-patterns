import spell.Spell
import spell.SpellDao
import spellbook.Spellbook
import spellbook.SpellbookDao
import wizard.Wizard
import wizard.WizardDao

class MagicService(
    private val wizardDao: WizardDao,
    private val spellbookDao: SpellbookDao,
    private val spellDao: SpellDao
) {
    fun findAllWizards(): List<Wizard> = wizardDao.findAll()
    fun findAllSpellbooks(): List<Spellbook> = spellbookDao.findAll()
    fun findAllSpells(): List<Spell> = spellDao.findAll()
    fun findWizardsWithSpellbook(name: String?): List<Wizard> = spellbookDao.findByName(name)!!.wizards.toList()
    fun findWizardsWithSpell(name: String?): List<Wizard> = spellDao.findByName(name)!!.spellbook!!.wizards.toList()
}