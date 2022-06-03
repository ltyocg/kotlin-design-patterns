package common

import HibernateUtil
import org.hibernate.Session
import org.hibernate.Transaction

abstract class Dao<E : BaseEntity> {
    @Suppress("UNCHECKED_CAST")
    val persistentClass = Class.forName(this::class.supertypes[0].arguments[0].toString()) as Class<E>

    fun find(id: Long?): E? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        criteria.where(criteriaBuilder.equal(criteria.from(persistentClass).get<E>("id"), id))
        it.createQuery(criteria).singleResultOrNull
    }

    fun persist(entity: E) = transaction { it.persist(entity) }
    fun merge(entity: E): E = transaction { it.merge(entity) }
    fun remove(entity: E) = transaction { it.remove(entity) }
    fun findAll(): List<E> = transaction { it.createQuery(it.criteriaBuilder.createQuery(persistentClass)).list() }

    protected fun <R> transaction(block: (Session) -> R): R {
        var transaction: Transaction? = null
        try {
            return HibernateUtil.getSessionFactory().openSession().use {
                transaction = it.beginTransaction()
                block(it)
            }
        } catch (e: Exception) {
            transaction?.rollback()
            transaction = null
            throw e
        } finally {
            transaction?.commit()
        }
    }
}