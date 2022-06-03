package spellbook

import common.Dao

class SpellbookDao : Dao<Spellbook>() {
    fun findByName(name: String): Spellbook? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        criteria.where(criteriaBuilder.equal(criteria.from(persistentClass).get<Spellbook>("name"), name))
        it.createQuery(criteria).singleResultOrNull
    }
}