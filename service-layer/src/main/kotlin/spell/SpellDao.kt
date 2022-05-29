package spell

import common.Dao

class SpellDao : Dao<Spell>() {
    fun findByName(name: String?): Spell? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        val root = criteria.from(persistentClass)
        criteria.where(criteriaBuilder.equal(root.get<Spell>("name"), name))
        it.createQuery(criteria).singleResultOrNull
    }
}