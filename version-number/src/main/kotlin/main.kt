import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val bookId = 1L
    val bookRepository = BookRepository()
    val book = Book().apply { id = bookId }
    bookRepository.add(book)
    log.info("An empty book with version {} was added to repository", book.version)
    val aliceBook = bookRepository[bookId]
    val bobBook = bookRepository[bookId]
    aliceBook.title = "Kama Sutra"
    bookRepository.update(aliceBook)
    log.info("Alice updates the book with new version {}", aliceBook.version)
    bobBook.author = "Vatsyayana Mallanaga"
    try {
        log.info("Bob tries to update the book with his version {}", bobBook.version)
        bookRepository.update(bobBook)
    } catch (e: VersionMismatchException) {
        log.info("Exception: {}", e.localizedMessage)
    }
}