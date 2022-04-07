package repository

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

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