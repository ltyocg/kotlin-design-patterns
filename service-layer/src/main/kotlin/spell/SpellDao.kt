package spell

import common.Dao

class SpellDao : Dao<Spell>() {
    fun findByName(name: String): Spell? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        criteria.where(criteriaBuilder.equal(criteria.from(persistentClass).get<Spell>("name"), name))
        it.createQuery(criteria).singleResultOrNull
    }
}