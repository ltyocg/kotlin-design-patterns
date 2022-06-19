import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class BusinessDelegateTest {
    private lateinit var netflixService: NetflixService
    private lateinit var youTubeService: YouTubeService
    private lateinit var businessDelegate: BusinessDelegate

    @BeforeTest
    fun setup() {
        netflixService = spy(NetflixService())
        youTubeService = spy(YouTubeService())
        businessDelegate = spy(BusinessDelegate(spy(BusinessLookup())))
    }

    @Test
    fun test() {
        val client = MobileClient(businessDelegate)
        client.playbackMovie("Die hard")
        verify(businessDelegate).playbackMovie(any())
        verify(netflixService).doProcessing()
        client.playbackMovie("Maradona")
        verify(businessDelegate, times(2)).playbackMovie(any())
        verify(youTubeService).doProcessing()
    }
}