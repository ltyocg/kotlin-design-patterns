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
        persist(Author(username = username, name = name, email = email))
    }

    override fun bookAddedToAuthor(title: String?, price: Double, username: String?) = transaction {
        persist(Book(title = title, price = price, author = getAuthorByUsername(username)))
    }

    override fun authorNameUpdated(username: String?, name: String?) = transaction {
        merge(getAuthorByUsername(username).also { it.name = name })
    }

    override fun authorUsernameUpdated(oldUsername: String?, newUsername: String?) = transaction {
        merge(getAuthorByUsername(oldUsername).also { it.username = newUsername })
    }

    override fun authorEmailUpdated(username: String?, email: String?) = transaction {
        merge(getAuthorByUsername(username).also { it.email = email })
    }

    override fun bookTitleUpdated(oldTitle: String?, newTitle: String?) = transaction {
        merge(getBookByTitle(oldTitle).also { it.title = newTitle })
    }

    override fun bookPriceUpdated(title: String?, price: Double) = transaction {
        merge(getBookByTitle(title).also { it.price = price })
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

    private inline fun transaction(block: Session.() -> Unit) = sessionFactory.openSession().use {
        val transaction = it.beginTransaction()
        try {
            block(it)
        } finally {
            transaction.commit()
        }
    }
}