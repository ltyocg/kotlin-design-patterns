import java.math.BigInteger

class QueryService {
    private val sessionFactory = HibernateUtil.sessionFactory
    fun getAuthorByUsername(username: String?): Author = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT a.username as \"username\", a.name as \"name\", a.email as \"email\" FROM Author a where a.username=:username", Author::class.java)
            .setParameter(AppConstants.USER_NAME, username)
            .singleResult as Author
    }

    fun getBook(title: String?): Book? = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT b.title as \"title\", b.price as \"price\" FROM Book b where b.title=:title")
            .setParameter("title", title)
            .uniqueResult()?.let {
                @Suppress("UNCHECKED_CAST")
                it as Array<Any?>
                Book(it[0] as String?, it[1] as Double)
            }
    }

    fun getAuthorBooks(username: String?): List<Book> = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT b.title as \"title\", b.price as \"price\" FROM Author a , Book b where b.author_id = a.id and a.username=:username")
            .setParameter(AppConstants.USER_NAME, username)
            .list().map {
                @Suppress("UNCHECKED_CAST")
                it as Array<Any?>
                Book(it[0] as String?, it[1] as Double)
            }
    }

    fun getAuthorBooksCount(username: String?): BigInteger = sessionFactory.openSession().use {
        val sqlQuery = it.createNativeQuery("SELECT count(b.title) FROM  Book b, Author a where b.author_id = a.id and a.username=:username")
        sqlQuery.setParameter(AppConstants.USER_NAME, username)
        sqlQuery.uniqueResult() as BigInteger
    }

    val authorsCount: BigInteger
        get() = sessionFactory.openSession().use {
            it.createNativeQuery("SELECT count(id) from Author")
                .uniqueResult() as BigInteger
        }
}
