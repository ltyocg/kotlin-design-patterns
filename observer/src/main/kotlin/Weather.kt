import io.github.oshai.kotlinlogging.KotlinLogging

class Weather {
    private val logger = KotlinLogging.logger {}
    private var currentWeather = WeatherType.SUNNY
    private val observers = mutableListOf<WeatherObserver>()
    fun addObserver(obs: WeatherObserver) {
        observers.add(obs)
    }

    fun removeObserver(obs: WeatherObserver) {
        observers.remove(obs)
    }

    fun timePasses() {
        val enumValues = WeatherType.entries
        currentWeather = enumValues[(currentWeather.ordinal + 1) % enumValues.size]
        logger.info { "The weather changed to $currentWeather." }
        observers.forEach { it.update(currentWeather) }
    }
}
