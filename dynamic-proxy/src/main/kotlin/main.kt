import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.reflect.Proxy
import java.net.http.HttpClient

fun main() {
    val app = App("https://jsonplaceholder.typicode.com", HttpClient.newHttpClient())
    app.createDynamicProxy()
    app.callMethods()
}

class App(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    private val logger = KotlinLogging.logger {}
    private lateinit var albumServiceProxy: AlbumService
    fun createDynamicProxy() {
        albumServiceProxy = Proxy.newProxyInstance(
            javaClass.classLoader,
            arrayOf(AlbumService::class.java),
            AlbumInvocationHandler(baseUrl, httpClient)
        ) as AlbumService
    }

    fun callMethods() {
        val albumId = 17
        val userId = 3
        val albums = albumServiceProxy.readAlbums()
        albums.forEach { logger.info { it } }
        val album = albumServiceProxy.readAlbum(albumId)
        logger.info { album }
        val newAlbum = albumServiceProxy.createAlbum(Album(title = "Big World", userId = userId))
        logger.info { newAlbum }
        val editAlbum = albumServiceProxy.updateAlbum(albumId, Album(title = "Green Valley", userId = userId))
        logger.info { editAlbum }
        val removedAlbum = albumServiceProxy.deleteAlbum(albumId)
        logger.info { removedAlbum }
    }
}
