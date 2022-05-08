package domain

import jakarta.persistence.*

@Entity
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var username: String? = null,
    var name: String? = null,
    var email: String? = null
)

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String? = null,
    var price: Double = 0.0,
    @ManyToOne
    var author: Author? = null
)