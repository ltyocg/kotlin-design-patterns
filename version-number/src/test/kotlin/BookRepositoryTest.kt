import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BookRepositoryTest {
    private val bookId: Long = 1
    private val bookRepository = BookRepository()

    @BeforeTest
    fun setUp() {
        val book = Book()
        book.id = bookId
        bookRepository.add(book)
    }

    @Test
    fun `default version remains zero after add`() = assertEquals(0, bookRepository[bookId].version)

    @Test
    fun `alice and bob have different versions after alice update`() {
        val aliceBook = bookRepository[bookId]
        val (_, title, _, version) = bookRepository[bookId]
        aliceBook.title = "Kama Sutra"
        bookRepository.update(aliceBook)
        assertEquals(1, aliceBook.version)
        assertEquals(0, version)
        with(bookRepository[bookId]) {
            assertEquals(aliceBook.version, this.version)
            assertEquals(aliceBook.title, this.title)
        }
        assertNotEquals(aliceBook.title, title)
    }

    @Test
    fun `should throw version mismatch exception on stale update`() {
        val aliceBook = bookRepository[bookId]
        val bobBook = bookRepository[bookId]
        aliceBook.title = "Kama Sutra"
        bookRepository.update(aliceBook)
        bobBook.author = "Vatsyayana Mallanaga"
        try {
            bookRepository.update(bobBook)
        } catch (e: VersionMismatchException) {
            assertEquals(0, bobBook.version)
            val version = bookRepository[bookId].version
            assertEquals(1, version)
            assertEquals(aliceBook.version, version)
            assertEquals("", bobBook.title)
            assertNotEquals(aliceBook.author, bobBook.author)
        }
    }
}