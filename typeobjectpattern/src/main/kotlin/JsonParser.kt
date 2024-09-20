import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class JsonParser {
    lateinit var candies: Map<String, Candy>
        private set

    fun parse() {
        @Serializable
        class Candies(val candies: List<Candy>)
        candies = javaClass.getResourceAsStream("candy.json")!!
            .bufferedReader()
            .use(BufferedReader::readText)
            .let { Json.decodeFromString<Candies>(it) }
            .candies
            .associateBy(Candy::name)
        candies.values.forEach { c ->
            c.parent = c.parentName?.let(candies::get)
            if (c.points == 0) c.parent?.run { c.points = points }
        }
    }
}