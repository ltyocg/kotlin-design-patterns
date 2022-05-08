import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    HibernateUtil.init()
    with(CommandServiceImpl()) {
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
    with(QueryServiceImpl()) {
        log.info("Author username : {}", getAuthorByUsername("username"))
        log.info("Author evans : {}", getAuthorByUsername(AppConstants.E_EVANS))
        log.info("jBloch number of books : {}", getAuthorBooksCount(AppConstants.J_BLOCH))
        log.info("Number of authors : {}", authorsCount)
        log.info("DDD book : {}", getBook("Domain-Driven Design"))
        log.info("jBloch books : {}", getAuthorBooks(AppConstants.J_BLOCH))
    }
}