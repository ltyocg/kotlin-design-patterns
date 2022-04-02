class VideoResource(
    private val fieldJsonMapper: FieldJsonMapper,
    private val videos: Map<Int, Video>
) {
    fun getDetails(id: Int, vararg fields: String): String =
        if (fields.isEmpty()) videos[id].toString()
        else fieldJsonMapper.toJson(videos[id], fields)
}