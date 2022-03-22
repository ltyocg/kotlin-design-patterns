import org.hibernate.HibernateException
import org.hibernate.Session
import org.slf4j.LoggerFactory
import utils.HibernateUtil

class UserService {
    private val log = LoggerFactory.getLogger(javaClass)
    private val factory = HibernateUtil.sessionFactory
    fun listUser(): List<User> {
        log.info("list all users.")
        try {
            factory.openSession().use {
                it.transaction {
                    return it.createQuery("FROM User").list().map { user -> user as User }
                }
            }
        } catch (e: HibernateException) {
            log.debug("fail to get users", e)
        }
        return emptyList()
    }

    fun createUser(user: User): Int {
        log.info("create user: {}", user.username)
        var id = -1
        try {
            factory.openSession().use {
                it.transaction { id = it.save(user) as Int }
            }
        } catch (e: HibernateException) {
            log.debug("fail to create user", e)
        }
        log.info("create user {} at {}", user.username, id)
        return id
    }

    fun updateUser(id: Int, user: User) {
        log.info("update user at {}", id)
        try {
            factory.openSession().use {
                it.transaction {
                    user.id = id
                    it.update(user)
                }
            }
        } catch (e: HibernateException) {
            log.debug("fail to update user", e)
        }
    }

    fun deleteUser(id: Int) {
        log.info("delete user at: {}", id)
        try {
            factory.openSession().use {
                it.transaction {
                    it.delete(it.get(User::class.java, id))
                }
            }
        } catch (e: HibernateException) {
            log.debug("fail to delete user", e)
        }
    }

    fun getUser(id: Int): User? {
        log.info("get user at: {}", id)
        try {
            factory.openSession().use {
                it.transaction {
                    return it.get(User::class.java, id)
                }
            }
        } catch (e: HibernateException) {
            log.debug("fail to get user", e)
        }
        return null
    }

    fun close() = HibernateUtil.shutdown()
    private inline fun Session.transaction(block: () -> Unit) {
        val transaction = beginTransaction()
        try {
            block()
        } finally {
            transaction.commit()
        }
    }
}
