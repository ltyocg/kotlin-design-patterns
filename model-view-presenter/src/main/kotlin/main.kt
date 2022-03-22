fun main() {
    FileSelectorPresenter(FileSelectorJFrame()).apply {
        loader = FileLoader()
        start()
    }
}