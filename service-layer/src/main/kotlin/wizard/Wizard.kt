package wizard

import common.BaseEntity
import jakarta.persistence.*
import spellbook.Spellbook

@Entity
@Table(name = "WIZARD")
class Wizard() : BaseEntity() {
    @Id
    @GeneratedValue
    @Column(name = "WIZARD_ID")
    override var id: Long? = null
    override var name: String? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    private var spellbooks = mutableSetOf<Spellbook>()

    constructor(name: String?) : this() {
        this.name = name
    }

    fun getSpellbooks(): Set<Spellbook> {
        return spellbooks
    }

    fun setSpellbooks(spellbooks: MutableSet<Spellbook>) {
        this.spellbooks = spellbooks
    }

    fun addSpellbook(spellbook: Spellbook) {
        spellbook.wizards.add(this)
        spellbooks.add(spellbook)
    }

    override fun toString(): String = name!!
}