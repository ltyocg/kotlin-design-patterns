interface FileSelectorView {
    fun open()
    fun close()
    val opened: Boolean
    var presenter: FileSelectorPresenter?
    var fileName: String?
    fun showMessage(message: String?)
    fun displayData(data: String?)
}