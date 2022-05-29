package spell

import common.BaseEntity
import jakarta.persistence.*
import spellbook.Spellbook

@Entity
@Table(name = "SPELL")
class Spell(override var name: String? = null) : BaseEntity() {
    @Id
    @GeneratedValue
    @Column(name = "SPELL_ID")
    override var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "SPELLBOOK_ID_FK", referencedColumnName = "SPELLBOOK_ID")
    var spellbook: Spellbook? = null
    override fun toString(): String = name!!
}