import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.HibernateException
import org.hibernate.Session
import utils.HibernateUtil

class UserService {
    private val logger = KotlinLogging.logger {}
    private val factory = HibernateUtil.sessionFactory
    fun listUser(): List<User> {
        logger.info { "list all users." }
        try {
            factory.openSession().use {
                it.transaction {
                    return it.createQuery("FROM User").list().map { user -> user as User }
                }
            }
        } catch (e: HibernateException) {
            logger.debug(e) { "fail to get users" }
        }
        return emptyList()
    }

    fun createUser(user: User): Int {
        logger.info { "create user: ${user.username}" }
        var id = -1
        try {
            factory.openSession().use {
                it.transaction { id = it.save(user) as Int }
            }
        } catch (e: HibernateException) {
            logger.debug(e) { "fail to create user" }
        }
        logger.info("create user {} at {}", user.username, id)
        return id
    }

    fun updateUser(id: Int, user: User) {
        logger.info { "update user at $id" }
        try {
            factory.openSession().use {
                it.transaction {
                    user.id = id
                    it.update(user)
                }
            }
        } catch (e: HibernateException) {
            logger.debug("fail to update user", e)
        }
    }

    fun deleteUser(id: Int) {
        logger.info("delete user at: {}", id)
        try {
            factory.openSession().use {
                it.transaction {
                    it.delete(it.get(User::class.java, id))
                }
            }
        } catch (e: HibernateException) {
            logger.debug(e) { "fail to delete user" }
        }
    }

    fun getUser(id: Int): User? {
        logger.info { "get user at: $id" }
        try {
            factory.openSession().use {
                it.transaction {
                    return it.get(User::class.java, id)
                }
            }
        } catch (e: HibernateException) {
            logger.debug(e) { "fail to get user" }
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
