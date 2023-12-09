import io.github.oshai.kotlinlogging.KotlinLogging

interface WeatherObserver {
    fun update(currentWeather: WeatherType)
}

class Hobbits : WeatherObserver {
    private val logger = KotlinLogging.logger {}
    override fun update(currentWeather: WeatherType) =
        logger.info { "The hobbits are facing ${currentWeather.description} weather now" }
}

class Orcs : WeatherObserver {
    private val logger = KotlinLogging.logger {}
    override fun update(currentWeather: WeatherType) =
        logger.info { "The orcs are facing ${currentWeather.description} weather now" }
}
