import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class OptimisticLockTest {
    private lateinit var cardUpdateService: CardUpdateService
    private lateinit var cardRepository: JpaRepository<Card>

    @BeforeTest
    fun setUp() {
        cardRepository = mock()
        cardUpdateService = CardUpdateService(cardRepository)
    }

    @Test
    fun `should not update entity on different version`() {
        val initialVersion = 1
        val cardId = 123L
        val card = Card(id = cardId, version = initialVersion, sum = 123f)
        whenever(cardRepository.findById(ArgumentMatchers.eq(cardId))).thenReturn(card)
        whenever(cardRepository.getEntityVersionById(Mockito.eq(cardId))).thenReturn(initialVersion + 1)
        assertFailsWith<ApplicationException> { cardUpdateService.doUpdate(card, cardId) }
    }

    @Test
    fun `should update on same version`() {
        val initialVersion = 1
        val cardId = 123L
        val card = Card(id = cardId, version = initialVersion, sum = 123f)
        whenever(cardRepository.findById(ArgumentMatchers.eq(cardId))).thenReturn(card)
        whenever(cardRepository.getEntityVersionById(Mockito.eq(cardId))).thenReturn(initialVersion)
        cardUpdateService.doUpdate(card, cardId)
        verify(cardRepository).update(any())
    }
}
