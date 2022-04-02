class Video(
    private val id: Int,
    private val title: String,
    private val length: Int,
    private val description: String,
    private val director: String,
    private val language: String
) {
    override fun toString(): String =
        "{\"id\": $id,\"title\": \"$title\",\"length\": $length,\"description\": \"$description\",\"director\": \"$director\",\"language\": \"$language\",}"
}