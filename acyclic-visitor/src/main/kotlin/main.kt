fun main() {
    val conUnix = ConfigureForUnixVisitor()
    val conDos = ConfigureForDosVisitor()
    val zoom = Zoom()
    val hayes = Hayes()
    hayes.accept(conDos)
    zoom.accept(conDos)
    hayes.accept(conUnix)
    zoom.accept(conUnix)
}