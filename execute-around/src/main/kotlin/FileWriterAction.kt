import java.io.FileWriter

fun interface FileWriterAction {
    fun writeFile(writer: FileWriter)
}