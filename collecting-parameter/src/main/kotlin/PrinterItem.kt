class PrinterItem(
    val paperSize: PaperSizes,
    pageCount: Int,
    val isDoubleSided: Boolean,
    val isColour: Boolean
) {
    val pageCount: Int

    init {
        require(pageCount > 0)
        this.pageCount = pageCount
    }
}