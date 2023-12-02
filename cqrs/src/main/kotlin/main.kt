import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    HibernateUtil.init()
    with(CommandService()) {
        authorCreated(AppConstants.E_EVANS, "Eric Evans", "evans@email.com")
        authorCreated(AppConstants.J_BLOCH, "Joshua Bloch", "jBloch@email.com")
        authorCreated(AppConstants.M_FOWLER, "Martin Fowler", "mFowler@email.com")
        bookAddedToAuthor("Domain-Driven Design", 60.08, AppConstants.E_EVANS)
        bookAddedToAuthor("Effective Java", 40.54, AppConstants.J_BLOCH)
        bookAddedToAuthor("Java Puzzlers", 39.99, AppConstants.J_BLOCH)
        bookAddedToAuthor("Java Concurrency in Practice", 29.40, AppConstants.J_BLOCH)
        bookAddedToAuthor("Patterns of Enterprise Application Architecture", 54.01, AppConstants.M_FOWLER)
        bookAddedToAuthor("Domain Specific Languages", 48.89, AppConstants.M_FOWLER)
        authorNameUpdated(AppConstants.E_EVANS, "Eric J. Evans")
    }
    with(QueryService()) {
        logger.info { "Author username : ${getAuthorByUsername("username")}" }
        logger.info { "Author evans : ${getAuthorByUsername(AppConstants.E_EVANS)}" }
        logger.info { "jBloch number of books : ${getAuthorBooksCount(AppConstants.J_BLOCH)}" }
        logger.info { "Number of authors : $authorsCount" }
        logger.info { "DDD book : ${getBook("Domain-Driven Design")}" }
        logger.info { "jBloch books : ${getAuthorBooks(AppConstants.J_BLOCH)}" }
    }
}
