package wizard

import common.Dao

class WizardDao : Dao<Wizard>() {
    fun findByName(name: String): Wizard? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        criteria.where(criteriaBuilder.equal(criteria.from(persistentClass).get<Wizard>("name"), name))
        it.createQuery(criteria).singleResultOrNull
    }
}