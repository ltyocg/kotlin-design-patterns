class BookRepository {
    private val collection = mutableMapOf<Long, Book>()
    fun add(book: Book) {
        val id = book.id
        if (collection.containsKey(id)) throw BookDuplicateException("Duplicated book with id: $id")
        collection[id] = book.copy()
    }

    fun update(book: Book) {
        val id = book.id
        if (!collection.containsKey(id)) throw BookNotFoundException("Not found book with id: $id")
        val latestBook = collection[book.id]!!
        if (book.version != latestBook.version) throw VersionMismatchException("Tried to update stale version ${book.version} while actual version is ${latestBook.version}")
        book.version++
        collection[book.id] = book.copy()
    }

    operator fun get(bookId: Long): Book {
        if (collection.containsKey(bookId)) return collection[bookId]?.copy() ?: Book()
        throw BookNotFoundException("Not found book with id: $bookId")
    }
}