import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val videoResource = VideoResource(FieldJsonMapper(), buildMap {
        put(1, Video(1, "Avatar", 178, "epic science fiction film", "James Cameron", "English"))
        put(2, Video(2, "Godzilla Resurgence", 120, "Action & drama movie|", "Hideaki Anno", "Japanese"))
        put(3, Video(3, "Interstellar", 169, "Adventure & Sci-Fi", "Christopher Nolan", "English"))
    })
    logger.info { "Retrieving full response from server:-" }
    logger.info { "Get all video information:" }
    logger.info { videoResource.getDetails(1) }
    logger.info { "----------------------------------------------------------" }
    logger.info { "Retrieving partial response from server:-" }
    logger.info { "Get video @id, @title, @director:" }
    logger.info { videoResource.getDetails(3, "id", "title", "director") }
    logger.info { "Get video @id, @length:" }
    logger.info { videoResource.getDetails(3, "id", "length") }
}
