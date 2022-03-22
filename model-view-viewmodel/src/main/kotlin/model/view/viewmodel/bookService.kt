package model.view.viewmodel

import org.springframework.stereotype.Service

interface BookService {
    fun load(): MutableList<Book>
}

@Service
class BookServiceImpl : BookService {
    private val designPatternBooks = mutableListOf(
        Book(
            "Head First Design Patterns: A Brain-Friendly Guide",
            "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson",
            "Head First Design Patterns Description"
        ),
        Book(
            "Design Patterns: Elements of Reusable Object-Oriented Software",
            "Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides",
            "Design Patterns Description"
        ),
        Book(
            "Patterns of Enterprise Application Architecture",
            "Martin Fowler",
            "Patterns of Enterprise Application Architecture Description"
        ),
        Book(
            "Design Patterns Explained",
            "Alan Shalloway, James Trott",
            "Design Patterns Explained Description"
        ),
        Book(
            "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development",
            "Craig Larman",
            "Applying UML and Patterns Description"
        )
    )

    override fun load(): MutableList<Book> = designPatternBooks
}