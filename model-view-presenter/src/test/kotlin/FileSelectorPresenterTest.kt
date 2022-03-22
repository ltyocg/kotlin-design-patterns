import kotlin.test.*

class FileSelectorPresenterTest {
    private lateinit var presenter: FileSelectorPresenter
    private lateinit var stub: FileSelectorStub
    private lateinit var loader: FileLoader

    @BeforeTest
    fun setUp() {
        stub = FileSelectorStub()
        loader = FileLoader()
        presenter = FileSelectorPresenter(stub)
        presenter.loader = loader
    }

    @Test
    fun wiring() {
        presenter.start()
        assertNotNull(stub.presenter)
        assertTrue(stub.opened)
    }

    @Test
    fun `update fileName to loader`() {
        val expectedFile = "Stamatis"
        stub.fileName = expectedFile
        presenter.start()
        presenter.fileNameChanged()
        assertEquals(expectedFile, loader.fileName)
    }

    @Test
    fun `file confirmation when name is null`() {
        stub.fileName = null
        presenter.start()
        presenter.fileNameChanged()
        presenter.confirmed()
        assertFalse(loader.isLoaded)
        assertEquals(1, stub.messagesSent)
    }

    @Test
    fun `file confirmation when file does not exist`() {
        stub.fileName = "RandomName.txt"
        presenter.start()
        presenter.fileNameChanged()
        presenter.confirmed()
        assertFalse(loader.isLoaded)
        assertEquals(1, stub.messagesSent)
    }

    @Test
    fun `file confirmation when file exists`() {
        stub.fileName = javaClass.getResource("test.txt")!!.file
        presenter.start()
        presenter.fileNameChanged()
        presenter.confirmed()
        assertTrue(loader.isLoaded)
        assertTrue(stub.dataDisplayed)
    }

    @Test
    fun cancellation() {
        presenter.start()
        presenter.cancelled()
        assertFalse(stub.isOpened)
    }
}