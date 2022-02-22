import java.math.BigInteger

interface IQueryService {
    fun getAuthorByUsername(username: String?): Author?
    fun getBook(title: String?): Book?
    fun getAuthorBooks(username: String?): List<Book>
    fun getAuthorBooksCount(username: String?): BigInteger
    val authorsCount: BigInteger
}

class QueryServiceImpl : IQueryService {
    private val sessionFactory = HibernateUtil.sessionFactory
    override fun getAuthorByUsername(username: String?): Author? = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT a.username as \"username\", a.name as \"name\", a.email as \"email\" FROM Author a where a.username=:username")
            .setParameter(AppConstants.USER_NAME, username)
            .uniqueResult()?.let {
                @Suppress("UNCHECKED_CAST")
                it as Array<Any?>
                Author(it[1] as String?, it[2] as String?, it[0] as String?)
            }
    }

    override fun getBook(title: String?): Book? = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT b.title as \"title\", b.price as \"price\" FROM Book b where b.title=:title")
            .setParameter("title", title)
            .uniqueResult()?.let {
                @Suppress("UNCHECKED_CAST")
                it as Array<Any?>
                Book(it[0] as String?, it[1] as Double)
            }
    }

    override fun getAuthorBooks(username: String?): List<Book> = sessionFactory.openSession().use { session ->
        session.createNativeQuery("SELECT b.title as \"title\", b.price as \"price\" FROM Author a , Book b where b.author_id = a.id and a.username=:username")
            .setParameter(AppConstants.USER_NAME, username)
            .list().map {
                @Suppress("UNCHECKED_CAST")
                it as Array<Any?>
                Book(it[0] as String?, it[1] as Double)
            }
    }

    override fun getAuthorBooksCount(username: String?): BigInteger = sessionFactory.openSession().use {
        val sqlQuery = it.createNativeQuery("SELECT count(b.title) FROM  Book b, Author a where b.author_id = a.id and a.username=:username")
        sqlQuery.setParameter(AppConstants.USER_NAME, username)
        sqlQuery.uniqueResult() as BigInteger
    }

    override val authorsCount: BigInteger
        get() = sessionFactory.openSession().use {
            it.createNativeQuery("SELECT count(id) from Author")
                .uniqueResult() as BigInteger
        }
}