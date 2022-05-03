import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertTrue

class ServantTest {
    @Test
    fun feed() {
        val royalty = mock<Royalty>()
        Servant("test").feed(royalty)
        verify(royalty).getFed()
        verifyNoMoreInteractions(royalty)
    }

    @Test
    fun `give wine`() {
        val royalty = mock<Royalty>()
        Servant("test").giveWine(royalty)
        verify(royalty).getDrink()
        verifyNoMoreInteractions(royalty)
    }

    @Test
    fun `give compliments`() {
        val royalty = mock<Royalty>()
        Servant("test").giveCompliments(royalty)
        verify(royalty).receiveCompliments()
        verifyNoMoreInteractions(royalty)
    }

    @Test
    fun `check if you will be hanged`() {
        val goodMoodRoyalty = mock<Royalty>()
        whenever(goodMoodRoyalty.mood).thenReturn(true)
        val badMoodRoyalty = mock<Royalty>()
        whenever(badMoodRoyalty.mood).thenReturn(true)
        assertTrue(Servant("test").checkIfYouWillBeHanged(listOf(goodMoodRoyalty, goodMoodRoyalty, goodMoodRoyalty)))
        assertTrue(Servant("test").checkIfYouWillBeHanged(listOf(goodMoodRoyalty, goodMoodRoyalty, badMoodRoyalty)))
    }
}