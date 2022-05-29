package spellbook

import common.BaseEntity
import jakarta.persistence.*
import spell.Spell
import wizard.Wizard

@Entity
@Table(name = "SPELLBOOK")
class Spellbook() : BaseEntity() {
    @Id
    @GeneratedValue
    @Column(name = "SPELLBOOK_ID")
    override var id: Long? = null
    override var name: String? = null

    @ManyToMany(mappedBy = "spellbooks", fetch = FetchType.EAGER)
    var wizards: MutableSet<Wizard>

    @OneToMany(mappedBy = "spellbook", orphanRemoval = true, cascade = [CascadeType.ALL])
    private var spells: MutableSet<Spell>

    init {
        spells = HashSet()
        wizards = HashSet()
    }

    constructor(name: String?) : this() {
        this.name = name
    }

    fun getSpells(): Set<Spell> {
        return spells
    }

    fun setSpells(spells: MutableSet<Spell>) {
        this.spells = spells
    }

    fun addSpell(spell: Spell) {
        spell.spellbook = this
        spells.add(spell)
    }

    override fun toString(): String {
        return name!!
    }
}