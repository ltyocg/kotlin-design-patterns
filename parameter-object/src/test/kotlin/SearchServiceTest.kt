import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchServiceTest {
    private val logger = KotlinLogging.logger {}
    private lateinit var parameterObject: ParameterObject
    private lateinit var searchService: SearchService

    @BeforeTest
    fun setUp() {
        parameterObject = ParameterObject("sneakers")
        searchService = SearchService()
    }

    @Test
    fun `default parameters match`() {
        assertEquals(
            searchService.search(parameterObject),
            searchService.search("sneakers", SortOrder.ASC),
            "Default Parameter values do not not match."
        )
        logger.info { "SortBy Default parameter value matches." }
        assertEquals(
            searchService.search(parameterObject),
            searchService.search("sneakers", "price"),
            "Default Parameter values do not not match."
        )
        logger.info { "SortOrder Default parameter value matches." }
        logger.info { "testDefaultParametersMatch executed successfully without errors." }
    }
}
