import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class VideoResourceTest {
    @Mock
    private lateinit var fieldJsonMapper: FieldJsonMapper
    private lateinit var resource: VideoResource

    @BeforeTest
    fun setUp() {
        resource = VideoResource(fieldJsonMapper, buildMap {
            put(1, Video(1, "Avatar", 178, "epic science fiction film", "James Cameron", "English"))
            put(2, Video(2, "Godzilla Resurgence", 120, "Action & drama movie|", "Hideaki Anno", "Japanese"))
            put(3, Video(3, "Interstellar", 169, "Adventure & Sci-Fi", "Christopher Nolan", "English"))
        })
    }

    @Test
    fun `should give video details by id`() = assertEquals(
        "{\"id\": 1,\"title\": \"Avatar\",\"length\": 178,\"description\": \"epic science fiction film\",\"director\": \"James Cameron\",\"language\": \"English\",}",
        resource.getDetails(1)
    )

    @Test
    fun `should give specified fields information of video`() {
        val fields = arrayOf("id", "title", "length")
        val expectedDetails = "{\"id\": 1,\"title\": \"Avatar\",\"length\": 178}"
        whenever(fieldJsonMapper.toJson(any(), eq(fields))).thenReturn(expectedDetails)
        assertEquals(expectedDetails, resource.getDetails(2, *fields))
    }
}