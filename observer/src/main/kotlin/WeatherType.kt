enum class WeatherType(val description: String) {
    SUNNY("Sunny"),
    RAINY("Rainy"),
    WINDY("Windy"),
    COLD("Cold");

    override fun toString(): String = name.lowercase()
}