import domain.Author
import domain.Book
import org.hibernate.Session

class CommandService {
    private val sessionFactory = HibernateUtil.sessionFactory
    fun authorCreated(username: String?, name: String?, email: String?) = transaction {
        persist(Author(username = username, name = name, email = email))
    }

    fun bookAddedToAuthor(title: String?, price: Double, username: String?) = transaction {
        persist(Book(title = title, price = price, author = getAuthorByUsername(username)))
    }

    fun authorNameUpdated(username: String?, name: String?) = transaction {
        merge(getAuthorByUsername(username).also { it.name = name })
    }

    fun authorUsernameUpdated(oldUsername: String?, newUsername: String?) = transaction {
        merge(getAuthorByUsername(oldUsername).also { it.username = newUsername })
    }

    fun authorEmailUpdated(username: String?, email: String?) = transaction {
        merge(getAuthorByUsername(username).also { it.email = email })
    }

    fun bookTitleUpdated(oldTitle: String?, newTitle: String?) = transaction {
        merge(getBookByTitle(oldTitle).also { it.title = newTitle })
    }

    fun bookPriceUpdated(title: String?, price: Double) = transaction {
        merge(getBookByTitle(title).also { it.price = price })
    }

    private fun Session.getAuthorByUsername(username: String?): Author =
        createSelectionQuery("from Author where username=:username", Author::class.java).apply {
            setParameter("username", username)
        }.uniqueResult() ?: throw NullPointerException("Author $username doesn't exist!")

    private fun Session.getBookByTitle(title: String?): Book =
        createSelectionQuery("from Book where title=:title", Book::class.java).apply {
            setParameter("title", title)
        }.uniqueResult() ?: throw NullPointerException("Author $title doesn't exist!")

    private inline fun transaction(block: Session.() -> Unit) = sessionFactory.openSession().use {
        val transaction = it.beginTransaction()
        try {
            block(it)
        } finally {
            transaction.commit()
        }
    }
}
