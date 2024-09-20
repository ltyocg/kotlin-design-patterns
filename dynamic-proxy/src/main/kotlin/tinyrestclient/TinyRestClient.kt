package tinyrestclient

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.springframework.web.util.UriUtils
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

class TinyRestClient(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    companion object {
        private val httpAnnotationByMethod = mutableMapOf<Method, Annotation?>()
    }

    private val logger = KotlinLogging.logger {}
    fun send(method: Method, args: Array<Any>): Any? {
        val httpAnnotation = method.getHttpAnnotation() ?: return null
        val httpResponse = httpClient.send(
            HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + buildUrl(method, args, httpAnnotation)))
                .header("Content-Type", "application/json")
                .method(httpAnnotation.annotationClass.simpleName!!.uppercase(), buildBodyPublisher(method, args))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )
        if (httpResponse.statusCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
            logger.error { "Error from server: ${httpResponse.body()}" }
            return null
        }
        return getResponse(method, httpResponse)
    }

    private fun buildUrl(method: Method, args: Array<Any>, httpMethodAnnotation: Annotation): String {
        var url = annotationValue(httpMethodAnnotation) ?: return ""
        for ((index, parameter) in method.parameters.withIndex()) {
            val pathAnnotation = parameter.declaredAnnotations.getAnnotationOf<Path>() ?: continue
            url = url.replace("{" + pathAnnotation.value + "}", UriUtils.encodePath(args[index].toString(), StandardCharsets.UTF_8))
        }
        return url
    }

    private fun buildBodyPublisher(method: Method, args: Array<Any>): HttpRequest.BodyPublisher {
        val index = method.parameters.indexOfFirst { it.declaredAnnotations.getAnnotationOf<Body>() != null }
        return if (index < 0) HttpRequest.BodyPublishers.noBody()
        else HttpRequest.BodyPublishers.ofString(Json.encodeToString(serializer(args[index]::class.java), args[index]))
    }

    private fun getResponse(method: Method, httpResponse: HttpResponse<String>): Any? {
        return Json.decodeFromString(
            try {
                method.genericReturnType
            } catch (e: Exception) {
                logger.error { "Cannot get the generic return type of the method ${method.name}()" }
                return null
            }.let { if (it is ParameterizedType) ListSerializer(serializer(it.actualTypeArguments[0])) else serializer(method.returnType) },
            httpResponse.body()
        )
    }


    private fun Method.getHttpAnnotation(): Annotation? = httpAnnotationByMethod.getOrPut(this) {
        declaredAnnotations.firstOrNull { it.annotationClass.java.isAnnotationPresent(Http::class.java) }
    }

    private inline fun <reified T> Array<Annotation>.getAnnotationOf(): T? = firstOrNull { it is T } as T?
    private fun annotationValue(annotation: Annotation): String? {
        val valueMethod = annotation.annotationClass.java.declaredMethods.firstOrNull { it.name === "value" } ?: return null
        val result = try {
            valueMethod.invoke(annotation)
        } catch (e: Exception) {
            logger.error(e) { "Cannot read the value ${annotation.annotationClass.simpleName}.${valueMethod.name}" }
            null
        }
        return if (result is String) result else null
    }
}
