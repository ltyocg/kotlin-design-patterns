import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FieldJsonMapperTest {
    private lateinit var mapper: FieldJsonMapper

    @BeforeTest
    fun setUp() {
        mapper = FieldJsonMapper()
    }

    @Test
    fun `should return json for specified fields in video`() {
        assertEquals(
            "{\"id\": 2,\"title\": \"Godzilla Resurgence\",\"length\": 120}",
            mapper.toJson(
                Video(2, "Godzilla Resurgence", 120, "Action & drama movie|", "Hideaki Anno", "Japanese"),
                arrayOf("id", "title", "length")
            )
        )
    }
}