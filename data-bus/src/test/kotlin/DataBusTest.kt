import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.never
import org.mockito.kotlin.then
import kotlin.test.BeforeTest
import kotlin.test.Test

class DataBusTest {
    @Mock
    private lateinit var member: Member

    @Mock
    private lateinit var event: DataType

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `published event is received by subscribed member`() {
        DataBus.subscribe(member)
        DataBus.publish(event)
        then(member).should()(event)
    }

    @Test
    fun `published event is not received by member after unsubscribing`() {
        DataBus.subscribe(member)
        DataBus.unsubscribe(member)
        DataBus.publish(event)
        then(member).should(never())(event)
    }
}