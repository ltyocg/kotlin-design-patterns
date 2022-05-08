import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class IntegrationTest {
    private lateinit var queryService: IQueryService

    @BeforeTest
    fun `initialize and populate database`() {
        HibernateUtil.init()
        with(CommandServiceImpl()) {
            authorCreated("username1", "name1", "email1")
            authorCreated("username2", "name2", "email2")
            authorEmailUpdated("username2", "new_email2")
            authorNameUpdated("username2", "new_name2")
            authorUsernameUpdated("username2", "new_username2")
            bookAddedToAuthor("title1", 10.0, "username1")
            bookAddedToAuthor("title2", 20.0, "username1")
            bookPriceUpdated("title2", 30.0)
            bookTitleUpdated("title2", "new_title2")
        }
        queryService = QueryServiceImpl()
    }

    @Test
    fun getAuthorByUsername() {
        val author = queryService.getAuthorByUsername("username1")!!
        assertEquals("username1", author.username)
        assertEquals("name1", author.name)
        assertEquals("email1", author.email)
    }

    @Test
    fun getUpdatedAuthorByUsername() =
        assertEquals(Author("new_name2", "new_email2", "new_username2"), queryService.getAuthorByUsername("new_username2"))

    @Test
    fun getBook() {
        val book = queryService.getBook("title1")!!
        assertEquals("title1", book.title)
        assertEquals(10.0, book.price, 0.01)
    }

    @Test
    fun getAuthorBooks() {
        val books = queryService.getAuthorBooks("username1")
        assertEquals(2, books.size)
        assertContains(books, Book("title1", 10.0))
        assertContains(books, Book("new_title2", 30.0))
    }

    @Test
    fun getAuthorBooksCount() = assertEquals(2.toBigInteger(), queryService.getAuthorBooksCount("username1"))

    @Test
    fun getAuthorsCount() = assertEquals(2.toBigInteger(), queryService.authorsCount)
}