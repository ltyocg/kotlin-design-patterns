import org.slf4j.LoggerFactory

interface WeatherObserver {
    fun update(currentWeather: WeatherType)
}

class Hobbits : WeatherObserver {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update(currentWeather: WeatherType) =
        log.info("The hobbits are facing {} weather now", currentWeather.description)
}

class Orcs : WeatherObserver {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update(currentWeather: WeatherType) =
        log.info("The orcs are facing " + currentWeather.description + " weather now")
}