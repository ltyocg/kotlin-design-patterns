package common

import HibernateUtil
import org.hibernate.Session
import org.hibernate.Transaction
import java.lang.reflect.ParameterizedType

abstract class Dao<E : BaseEntity> {
    val persistentClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<E>

    fun find(id: Long?): E? = transaction {
        val criteriaBuilder = it.criteriaBuilder
        val criteria = criteriaBuilder.createQuery(persistentClass)
        val root = criteria.from(persistentClass)
        criteria.where(criteriaBuilder.equal(root.get<E>("id"), id))
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