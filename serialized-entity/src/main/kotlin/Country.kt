import java.io.Serializable

data class Country(
    val code: Int,
    val name: String,
    val continents: String,
    val language: String
) : Serializable