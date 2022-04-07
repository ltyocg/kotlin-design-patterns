package repository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * repository.Person entity.
 */
@Entity
class Person(
    @Id
    @GeneratedValue
    private val id: Long? = null,
    var name: String,
    var surname: String,
    var age: Int

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Person
        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (age != other.age) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + age
        return result
    }
}