import Book.*
import java.time.LocalDate

data class Book(
    val genre: Genre,
    val author: String,
    val title: String,
    val publicationDate: LocalDate
) {
    fun interface AddGenre {
        fun withGenre(genre: Genre): AddAuthor
    }

    fun interface AddAuthor {
        fun withAuthor(author: String): AddTitle
    }

    fun interface AddTitle {
        fun withTitle(title: String): AddPublicationDate
    }

    fun interface AddPublicationDate {
        fun withPublicationDate(publicationDate: LocalDate): Book
    }

    companion object {
        internal val bookCreator: (Genre) -> (String) -> (String) -> (LocalDate) -> Book =
            { bookGenre ->
                { bookAuthor ->
                    { bookTitle ->
                        { bookPublicationDate -> Book(bookGenre, bookAuthor, bookTitle, bookPublicationDate) }
                    }
                }
            }

        fun builder(): AddGenre =
            AddGenre { genre ->
                AddAuthor { author ->
                    AddTitle { title ->
                        AddPublicationDate { publicationDate -> Book(genre, author, title, publicationDate) }
                    }
                }
            }
    }
}
