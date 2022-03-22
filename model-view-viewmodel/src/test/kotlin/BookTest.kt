import model.view.viewmodel.Book
import model.view.viewmodel.BookServiceImpl
import model.view.viewmodel.BookViewModel
import kotlin.test.*

class BookTest {
    private lateinit var bvm: BookViewModel
    private lateinit var testBook: Book
    private lateinit var testBookList: List<Book>
    private lateinit var testBookTwo: Book
    private lateinit var testBookThree: Book

    @BeforeTest
    fun setUp() {
        bvm = BookViewModel(BookServiceImpl())
        testBook = Book(
            "Head First Design Patterns: A Brain-Friendly Guide",
            "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson",
            "Head First Design Patterns Description"
        )
        testBookList = bvm.getBookList()
        testBookTwo = Book(
            "Head First Design Patterns: A Brain-Friendly Guide",
            "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson",
            "Head First Design Patterns Description"
        )
        testBookThree = Book(
            "Design Patterns: Elements of Reusable Object-Oriented Software",
            "Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides",
            "Design Patterns Description"
        )
    }

    @Test
    fun `test equals`() = assertEquals(testBook, testBookTwo)

    @Test
    fun `test toString`() {
        assertEquals(testBook.toString(), testBookTwo.toString())
        assertNotEquals(testBook.toString(), testBookThree.toString())
    }

    @Test
    fun `test hashCode`() {
        assertEquals(testBook, testBookTwo)
        assertEquals(testBookTwo, testBook)
        assertEquals(testBook.hashCode(), testBookTwo.hashCode())
    }

    @Test
    fun `load data`() = assertContains(testBookList[0].toString(), "Head First Design Patterns")

    @Test
    fun `selected data`() {
        bvm.selectedBook = testBook
        assertNotNull(bvm.selectedBook)
        assertEquals(testBook.toString(), bvm.selectedBook.toString())
    }

    @Test
    fun `delete data`() {
        bvm.selectedBook = testBook
        assertNotNull(bvm.selectedBook)
        assertContains(testBookList[0].toString(), "Head First Design Patterns")
        bvm.deleteBook()
        assertNull(bvm.selectedBook)
        assertFalse("Head First Design Patterns" in testBookList[0].toString())
    }
}