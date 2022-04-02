import java.lang.reflect.Field

class FieldJsonMapper {
    fun toJson(video: Video?, fields: Array<out String>): String =
        fields.joinToString(",", "{", "}") { getString(video, Video::class.java.getDeclaredField(it)) }

    private fun getString(video: Video?, declaredField: Field): String {
        declaredField.isAccessible = true
        val value = declaredField[video]
        return if (value is Int) "\"${declaredField.name}\": $value"
        else "\"${declaredField.name}\": \"$value\""
    }
}