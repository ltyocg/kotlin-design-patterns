import common.BaseEntity
import common.Dao
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.*

abstract class BaseDaoTest<E : BaseEntity, D : Dao<E>>(
    private val factory: (String) -> E,
    protected val dao: D
) {
    private companion object {
        private const val INITIAL_COUNT = 5
        private val ID_GENERATOR = AtomicInteger()
    }

    @BeforeTest
    fun setUp() = repeat(INITIAL_COUNT) {
        dao.persist(factory("${dao.persistentClass.simpleName}${ID_GENERATOR.incrementAndGet()}"))
    }

    @AfterTest
    fun tearDown() = HibernateUtil.dropSession()

    @Test
    fun find() {
        dao.findAll().forEach {
            val byId = dao.find(it.id)
            assertNotNull(byId)
            assertEquals(byId.id, byId.id)
        }
    }

    @Test
    fun remove() {
        val originalEntities = dao.findAll()
        dao.remove(originalEntities[1])
        dao.remove(originalEntities[2])
        val entitiesLeft = dao.findAll()
        assertEquals(INITIAL_COUNT - 2, entitiesLeft.size)
    }

    @Test
    fun findAll() = assertEquals(INITIAL_COUNT, dao.findAll().size)

    @Test
    fun setId() {
        val entity = factory("name")
        assertNull(entity.id)
        val expectedId = 1L
        entity.id = expectedId
        assertEquals(expectedId, entity.id)
    }

    @Test
    fun setName() {
        val entity = factory("name")
        assertEquals("name", entity.name)
        assertEquals("name", entity.toString())
        val expectedName = "new name"
        entity.name = expectedName
        assertEquals(expectedName, entity.name)
        assertEquals(expectedName, entity.toString())
    }
}