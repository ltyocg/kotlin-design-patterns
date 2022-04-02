import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val videoResource = VideoResource(FieldJsonMapper(), buildMap {
        put(1, Video(1, "Avatar", 178, "epic science fiction film", "James Cameron", "English"))
        put(2, Video(2, "Godzilla Resurgence", 120, "Action & drama movie|", "Hideaki Anno", "Japanese"))
        put(3, Video(3, "Interstellar", 169, "Adventure & Sci-Fi", "Christopher Nolan", "English"))
    })
    log.info("Retrieving full response from server:-")
    log.info("Get all video information:")
    log.info(videoResource.getDetails(1))
    log.info("----------------------------------------------------------")
    log.info("Retrieving partial response from server:-")
    log.info("Get video @id, @title, @director:")
    log.info(videoResource.getDetails(3, "id", "title", "director"))
    log.info("Get video @id, @length:")
    log.info(videoResource.getDetails(3, "id", "length"))
}