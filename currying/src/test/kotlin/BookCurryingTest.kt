import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class BookCurryingTest {
    private val expectedBook = Book(Genre.FANTASY, "Dave", "Into the Night", LocalDate.of(2002, 4, 7))

    @Test
    fun `creates expected book`() {
        assertEquals(
            expectedBook,
            Book.builder()
                .withGenre(Genre.FANTASY)
                .withAuthor("Dave")
                .withTitle("Into the Night")
                .withPublicationDate(LocalDate.of(2002, 4, 7))
        )
        assertEquals(
            expectedBook,
            Book.bookCreator(Genre.FANTASY)("Dave")("Into the Night")(LocalDate.of(2002, 4, 7))
        )
    }

    @Test
    fun `function creates expected book`() {
        val daveFantasyBookFunc = Book.builder()
            .withGenre(Genre.FANTASY)
            .withAuthor("Dave")
        assertEquals(
            expectedBook,
            daveFantasyBookFunc
                .withTitle("Into the Night")
                .withPublicationDate(LocalDate.of(2002, 4, 7))
        )
    }
}