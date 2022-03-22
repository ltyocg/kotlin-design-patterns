class FileSelectorStub : FileSelectorView {
    override var presenter: FileSelectorPresenter? = null
    override var fileName: String? = ""
    var dataDisplayed = false
        private set
    var isOpened = false
    var messagesSent = 0
        private set

    override fun open() {
        isOpened = true
    }

    override fun showMessage(message: String?) {
        messagesSent++
    }

    override fun close() {
        isOpened = false
    }

    override val opened: Boolean
        get() = isOpened

    override fun displayData(data: String?) {
        dataDisplayed = true
    }
}