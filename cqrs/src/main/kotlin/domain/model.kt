package domain

import javax.persistence.*

@Entity
class Author(
    var username: String?,
    var name: String?,
    var email: String?
) {
    constructor() : this(null, null, null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

@Entity
class Book(
    var title: String?,
    var price: Double,
    @ManyToOne
    var author: Author?
) {
    constructor() : this(null, 0.0, null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}