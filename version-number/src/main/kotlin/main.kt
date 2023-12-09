import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val bookId = 1L
    val bookRepository = BookRepository()
    val book = Book().apply { id = bookId }
    bookRepository.add(book)
    logger.info { "An empty book with version ${book.version} was added to repository" }
    val aliceBook = bookRepository[bookId]
    val bobBook = bookRepository[bookId]
    aliceBook.title = "Kama Sutra"
    bookRepository.update(aliceBook)
    logger.info { "Alice updates the book with new version ${aliceBook.version}" }
    bobBook.author = "Vatsyayana Mallanaga"
    try {
        logger.info { "Bob tries to update the book with his version ${bobBook.version}" }
        bookRepository.update(bobBook)
    } catch (e: VersionMismatchException) {
        logger.info { "Exception: ${e.localizedMessage}" }
    }
}
