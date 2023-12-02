import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.LocalDate

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Librarian begins their work." }
    val fantasyBookFunc = Book.builder().withGenre(Genre.FANTASY)
    val horrorBookFunc = Book.builder().withGenre(Genre.HORROR)
    val scifiBookFunc = Book.builder().withGenre(Genre.SCIFI)
    val kingFantasyBooksFunc = fantasyBookFunc.withAuthor("Stephen King")
    val kingHorrorBooksFunc = horrorBookFunc.withAuthor("Stephen King")
    val rowlingFantasyBooksFunc = fantasyBookFunc.withAuthor("J.K. Rowling")
    val shining = kingHorrorBooksFunc
        .withTitle("The Shining")
        .withPublicationDate(LocalDate.of(1977, 1, 28))
    val darkTower = kingFantasyBooksFunc
        .withTitle("The Dark Tower: Gunslinger")
        .withPublicationDate(LocalDate.of(1982, 6, 10))
    val chamberOfSecrets = rowlingFantasyBooksFunc
        .withTitle("Harry Potter and the Chamber of Secrets")
        .withPublicationDate(LocalDate.of(1998, 7, 2))
    val dune = scifiBookFunc
        .withAuthor("Frank Herbert")
        .withTitle("Dune")
        .withPublicationDate(LocalDate.of(1965, 8, 1))
    val foundation = scifiBookFunc
        .withAuthor("Isaac Asimov")
        .withTitle("Foundation")
        .withPublicationDate(LocalDate.of(1942, 5, 1))
    logger.info { "Stephen King Books:" }
    logger.info { shining }
    logger.info { darkTower }
    logger.info { "J.K. Rowling Books:" }
    logger.info { chamberOfSecrets }
    logger.info { "Sci-fi Books:" }
    logger.info { dune }
    logger.info { foundation }
}
