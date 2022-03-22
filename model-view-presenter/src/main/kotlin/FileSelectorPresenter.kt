import java.io.Serializable

class FileSelectorPresenter(private val view: FileSelectorView) : Serializable {
    lateinit var loader: FileLoader
    fun start() {
        view.presenter = this
        view.open()
    }

    fun fileNameChanged() {
        loader.fileName = view.fileName ?: ""
    }

    fun confirmed() {
        if (loader.fileName.isEmpty()) {
            view.showMessage("Please give the name of the file first!")
            return
        }
        if (loader.fileExists()) view.displayData(loader.loadData())
        else view.showMessage("The file specified does not exist.")
    }

    fun cancelled() {
        view.close()
    }
}