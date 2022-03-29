import java.awt.Desktop
import java.io.File
import java.net.URLDecoder

class App

fun main() {
    val applicationFile = File(URLDecoder.decode(App::class.java.classLoader.getResource("sample-ui/login.html")!!.file, Charsets.UTF_8))
    if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(applicationFile)
    else Runtime.getRuntime().exec("cmd.exe start $applicationFile")
}