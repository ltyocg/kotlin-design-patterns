package model.view.viewmodel

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookViewModel(private val bookService: BookService) {
    var selectedBook: Book? = null
        @GetMapping("selectedBook") set

    @GetMapping("getBookList")
    fun getBookList(): MutableList<Book> = bookService.load()

    @GetMapping("deleteBook")
    fun deleteBook() {
        if (selectedBook != null) {
            getBookList().remove(selectedBook)
            selectedBook = null
        }
    }
}