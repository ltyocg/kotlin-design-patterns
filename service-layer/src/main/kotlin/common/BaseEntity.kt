package common

import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class BaseEntity {
    abstract var id: Long?
    abstract var name: String?
}