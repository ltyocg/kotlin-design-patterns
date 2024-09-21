package repository

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class PersonSpecifications {
    class AgeBetweenSpec(private val from: Int, private val to: Int) : Specification<Person?> {
        override fun toPredicate(root: Root<Person?>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? =
            cb.between(root.get("age"), from, to)
    }

    class NameEqualSpec(val name: String) : Specification<Person?> {
        override fun toPredicate(root: Root<Person?>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? =
            cb.equal(root.get<Any>("name"), name)
    }
}
