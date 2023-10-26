import org.slf4j.LoggerFactory
import java.time.LocalDate

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Librarian begins their work.")
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
    log.info("Stephen King Books:")
    log.info(shining.toString())
    log.info(darkTower.toString())
    log.info("J.K. Rowling Books:")
    log.info(chamberOfSecrets.toString())
    log.info("Sci-fi Books:")
    log.info(dune.toString())
    log.info(foundation.toString())
}