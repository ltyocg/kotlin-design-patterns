import kotlin.test.*

class ServiceContextTest {
    private lateinit var layerA: LayerA

    @BeforeTest
    fun initiateLayerA() {
        layerA = LayerA()
    }

    @Test
    fun `same context passed between layers`() {
        val context1 = layerA.context
        val layerB = LayerB(layerA)
        sequenceOf(
            context1,
            layerB.context,
            LayerC(layerB).context
        ).zipWithNext(::assertSame)
    }

    @Test
    fun `scoped data passed between layers`() {
        layerA.addAccountInfo(SERVICE)
        val context = layerA.let(::LayerB).let(::LayerC).also { it.addSearchInfo(SERVICE) }.context
        assertEquals(SERVICE, context.accountService)
        assertNull(context.sessionService)
        assertEquals(SERVICE, context.searchService)
    }

    @Test
    fun `layer contexts`() {
        assertNull(layerA.context.accountService)
        assertNull(layerA.context.searchService)
        assertNull(layerA.context.sessionService)
        layerA.addAccountInfo(SERVICE)
        assertEquals(SERVICE, layerA.context.accountService)
        assertNull(layerA.context.searchService)
        assertNull(layerA.context.sessionService)
        val layerB = LayerB(layerA).also { it.addSessionInfo(SERVICE) }
        assertEquals(SERVICE, layerB.context.accountService)
        assertEquals(SERVICE, layerB.context.sessionService)
        assertNull(layerB.context.searchService)
        val layerC = LayerC(layerB).also { it.addSearchInfo(SERVICE) }
        assertEquals(SERVICE, layerC.context.accountService)
        assertEquals(SERVICE, layerC.context.searchService)
        assertEquals(SERVICE, layerC.context.sessionService)
    }

    companion object {
        private const val SERVICE = "SERVICE"
    }
}
