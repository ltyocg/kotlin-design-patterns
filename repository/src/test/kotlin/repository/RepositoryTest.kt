package repository

import jakarta.annotation.Resource
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [AppConfig::class])
class RepositoryTest {
    private val terry = Person(name = "Terry", surname = "Law", age = 36)
    private val persons = listOf(
        Person(name = "Peter", surname = "Sagan", age = 17),
        Person(name = "Nasta", surname = "Kuzminova", age = 25),
        Person(name = "John", surname = "lawrence", age = 35),
        terry
    )

    @Resource
    private lateinit var repository: PersonRepository

    @BeforeTest
    fun setup() {
        repository.saveAll(persons)
    }

    @Test
    fun findAll() {
        val actuals = repository.findAll()
        assertTrue(actuals.containsAll(persons))
        assertTrue(persons.containsAll(actuals))
    }

    @Test
    fun save() {
        repository.save(repository.findByName("Terry")!!.apply {
            surname = "Lee"
            age = 47
        })
        val terry = repository.findByName("Terry")!!
        assertEquals("Lee", terry.surname)
        assertEquals(47, terry.age)
    }

    @Test
    fun delete() {
        repository.delete(repository.findByName("Terry")!!)
        assertEquals(3, repository.count())
        assertNull(repository.findByName("Terry"))
    }

    @Test
    fun count() = assertEquals(4, repository.count())

    @Test
    fun `findAll by AgeBetweenSpec`() {
        val persons = repository.findAll(PersonSpecifications.AgeBetweenSpec(20, 40))
        assertEquals(3, persons.size)
        assertTrue(persons.all { it.age in 21..39 })
    }

    @Test
    fun `findOne by NameEqualSpec`() {
        val actual = repository.findOne(PersonSpecifications.NameEqualSpec("Terry"))
        assertTrue(actual.isPresent)
        assertEquals(terry, actual.get())
    }

    @AfterTest
    fun cleanup() = repository.deleteAll()
}