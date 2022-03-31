import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchServiceTest {
    private val log = LoggerFactory.getLogger(SearchServiceTest::class.java)
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
        log.info("SortBy Default parameter value matches.")
        assertEquals(
            searchService.search(parameterObject),
            searchService.search("sneakers", "price"),
            "Default Parameter values do not not match."
        )
        log.info("SortOrder Default parameter value matches.")
        log.info("testDefaultParametersMatch executed successfully without errors.")
    }
}