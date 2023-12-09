import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.test.Test
import kotlin.test.assertEquals

class ParameterObjectTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `default sortBy`() {
        assertEquals(
            ParameterObject.DEFAULT_SORT_BY,
            ParameterObject("sneakers", sortOrder = SortOrder.DESC).sortBy,
            "Default SortBy is not set."
        )
        logger.info { "SortBy Default parameter value is set during object creation as no value is passed." }
    }

    @Test
    fun `default sortOrder`() {
        assertEquals(
            ParameterObject.DEFAULT_SORT_ORDER,
            ParameterObject("sneakers", "brand").sortOrder,
            "Default SortOrder is not set."
        )
        logger.info { "SortOrder Default parameter value is set during object creation as no value is passed." }
    }
}
