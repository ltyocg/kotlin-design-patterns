import domain.Author
import domain.Book
import org.hibernate.Session

interface ICommandService {
    fun authorCreated(username: String?, name: String?, email: String?)
    fun bookAddedToAuthor(title: String?, price: Double, username: String?)
    fun authorNameUpdated(username: String?, name: String?)
    fun authorUsernameUpdated(oldUsername: String?, newUsername: String?)
    fun authorEmailUpdated(username: String?, email: String?)
    fun bookTitleUpdated(oldTitle: String?, newTitle: String?)
    fun bookPriceUpdated(title: String?, price: Double)
}

class CommandServiceImpl : ICommandService {
    private val sessionFactory = HibernateUtil.sessionFactory
    override fun authorCreated(username: String?, name: String?, email: String?) = transaction {
        save(Author(username, name, email))
    }

    override fun bookAddedToAuthor(title: String?, price: Double, username: String?) = transaction {
        save(Book(title, price, getAuthorByUsername(username)))
    }

    override fun authorNameUpdated(username: String?, name: String?) = transaction {
        update(getAuthorByUsername(username).also { it.name = name })
    }

    override fun authorUsernameUpdated(oldUsername: String?, newUsername: String?) = transaction {
        update(getAuthorByUsername(oldUsername).also { it.username = newUsername })
    }

    override fun authorEmailUpdated(username: String?, email: String?) = transaction {
        update(getAuthorByUsername(username).also { it.email = email })
    }

    override fun bookTitleUpdated(oldTitle: String?, newTitle: String?) = transaction {
        update(getBookByTitle(oldTitle).also { it.title = newTitle })
    }

    override fun bookPriceUpdated(title: String?, price: Double) = transaction {
        update(getBookByTitle(title).also { it.price = price })
    }

    private fun Session.getAuthorByUsername(username: String?): Author {
        val query = createQuery("from Author where username=:username")
        query.setParameter("username", username)
        return query.uniqueResult() as Author? ?: throw NullPointerException("Author $username doesn't exist!")
    }

    private fun Session.getBookByTitle(title: String?): Book {
        val query = createQuery("from Book where title=:title")
        query.setParameter("title", title)
        return query.uniqueResult() as Book? ?: throw NullPointerException("Author $title doesn't exist!")
    }

    private fun transaction(block: Session.() -> Unit) {
        sessionFactory.openSession().use {
            it.beginTransaction()
            block(it)
            it.transaction.commit()
        }
    }
}