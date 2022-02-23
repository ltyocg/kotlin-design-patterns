import kotlin.test.*

class InMemoryTicketRepositoryTest {
    private val repository: LotteryTicketRepository = InMemoryTicketRepository()

    @BeforeTest
    fun clear() {
        repository.deleteAll()
    }

    @Test
    fun `test crud operations`() {
        val repository = InMemoryTicketRepository()
        assertTrue(repository.findAll().isEmpty())
        val id = repository.save(LotteryTestUtils.createLotteryTicket())
        assertEquals(1, repository.findAll().size)
        assertNotNull(repository.findById(id))
    }
}
