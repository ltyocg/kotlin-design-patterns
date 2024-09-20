import io.github.oshai.kotlinlogging.KotlinLogging
import tinyrestclient.TinyRestClient
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.net.http.HttpClient

class AlbumInvocationHandler(baseUrl: String, httpClient: HttpClient) : InvocationHandler {
    private val logger = KotlinLogging.logger {}
    private val restClient = TinyRestClient(baseUrl, httpClient)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any? {
        logger.info { "===== Calling the method ${method.declaringClass.simpleName}.${method.name}()" }
        return restClient.send(method, args ?: emptyArray())
    }
}
