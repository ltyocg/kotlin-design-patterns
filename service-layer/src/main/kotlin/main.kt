import io.github.oshai.kotlinlogging.KotlinLogging
import spell.Spell
import spell.SpellDao
import spellbook.Spellbook
import spellbook.SpellbookDao
import wizard.Wizard
import wizard.WizardDao

private const val BOOK_OF_IDORES = "Book of Idores"
private val logger = KotlinLogging.logger {}
fun main() {
    initData()
    queryData()
}

private fun initData() {
    val spell1 = Spell("Ice dart")
    val spell2 = Spell("Invisibility")
    val spell3 = Spell("Stun bolt")
    val spell4 = Spell("Confusion")
    val spell5 = Spell("Darkness")
    val spell6 = Spell("Fireball")
    val spell7 = Spell("Enchant weapon")
    val spell8 = Spell("Rock armour")
    val spell9 = Spell("Light")
    val spell10 = Spell("Bee swarm")
    val spell11 = Spell("Haste")
    val spell12 = Spell("Levitation")
    val spell13 = Spell("Magic lock")
    val spell14 = Spell("Summon hell bat")
    val spell15 = Spell("Water walking")
    val spell16 = Spell("Magic storm")
    val spell17 = Spell("Entangle")
    val spellDao = SpellDao()
    spellDao.persist(spell1)
    spellDao.persist(spell2)
    spellDao.persist(spell3)
    spellDao.persist(spell4)
    spellDao.persist(spell5)
    spellDao.persist(spell6)
    spellDao.persist(spell7)
    spellDao.persist(spell8)
    spellDao.persist(spell9)
    spellDao.persist(spell10)
    spellDao.persist(spell11)
    spellDao.persist(spell12)
    spellDao.persist(spell13)
    spellDao.persist(spell14)
    spellDao.persist(spell15)
    spellDao.persist(spell16)
    spellDao.persist(spell17)
    val spellbookDao = SpellbookDao()
    val spellbook1 = Spellbook("Book of Orgymon")
    spellbookDao.persist(spellbook1)
    spellbook1.addSpell(spell1)
    spellbook1.addSpell(spell2)
    spellbook1.addSpell(spell3)
    spellbook1.addSpell(spell4)
    spellbookDao.merge(spellbook1)
    val spellbook2 = Spellbook("Book of Aras")
    spellbookDao.persist(spellbook2)
    spellbook2.addSpell(spell5)
    spellbook2.addSpell(spell6)
    spellbookDao.merge(spellbook2)
    val spellbook3 = Spellbook("Book of Kritior")
    spellbookDao.persist(spellbook3)
    spellbook3.addSpell(spell7)
    spellbook3.addSpell(spell8)
    spellbook3.addSpell(spell9)
    spellbookDao.merge(spellbook3)
    val spellbook4 = Spellbook("Book of Tamaex")
    spellbookDao.persist(spellbook4)
    spellbook4.addSpell(spell10)
    spellbook4.addSpell(spell11)
    spellbook4.addSpell(spell12)
    spellbookDao.merge(spellbook4)
    val spellbook5 = Spellbook(BOOK_OF_IDORES)
    spellbookDao.persist(spellbook5)
    spellbook5.addSpell(spell13)
    spellbookDao.merge(spellbook5)
    val spellbook6 = Spellbook("Book of Opaen")
    spellbookDao.persist(spellbook6)
    spellbook6.addSpell(spell14)
    spellbook6.addSpell(spell15)
    spellbookDao.merge(spellbook6)
    val spellbook7 = Spellbook("Book of Kihione")
    spellbookDao.persist(spellbook7)
    spellbook7.addSpell(spell16)
    spellbook7.addSpell(spell17)
    spellbookDao.merge(spellbook7)
    val wizardDao = WizardDao()
    val wizard1 = Wizard("Aderlard Boud")
    wizardDao.persist(wizard1)
    wizard1.addSpellbook(spellbookDao.findByName("Book of Orgymon")!!)
    wizard1.addSpellbook(spellbookDao.findByName("Book of Aras")!!)
    wizardDao.merge(wizard1)
    val wizard2 = Wizard("Anaxis Bajraktari")
    wizardDao.persist(wizard2)
    wizard2.addSpellbook(spellbookDao.findByName("Book of Kritior")!!)
    wizard2.addSpellbook(spellbookDao.findByName("Book of Tamaex")!!)
    wizardDao.merge(wizard2)
    val wizard3 = Wizard("Xuban Munoa")
    wizardDao.persist(wizard3)
    wizard3.addSpellbook(spellbookDao.findByName(BOOK_OF_IDORES)!!)
    wizard3.addSpellbook(spellbookDao.findByName("Book of Opaen")!!)
    wizardDao.merge(wizard3)
    val wizard4 = Wizard("Blasius Dehooge")
    wizardDao.persist(wizard4)
    wizard4.addSpellbook(spellbookDao.findByName("Book of Kihione")!!)
    wizardDao.merge(wizard4)
}

private fun queryData() {
    val service = MagicService(WizardDao(), SpellbookDao(), SpellDao())
    logger.info { "Enumerating all wizards" }
    service.findAllWizards().map(Wizard::name).forEach { logger.info { it } }
    logger.info { "Enumerating all spellbooks" }
    service.findAllSpellbooks().map(Spellbook::name).forEach { logger.info { it } }
    logger.info { "Enumerating all spells" }
    service.findAllSpells().map(Spell::name).forEach { logger.info { it } }
    logger.info { "Find wizards with spellbook 'Book of Idores'" }
    service.findWizardsWithSpellbook(BOOK_OF_IDORES).forEach { logger.info { "${it.name} has 'Book of Idores'" } }
    logger.info { "Find wizards with spell 'Fireball'" }
    service.findWizardsWithSpell("Fireball").forEach { logger.info { "${it.name} has 'Fireball'" } }
}
