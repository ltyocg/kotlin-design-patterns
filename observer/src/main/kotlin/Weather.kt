import org.slf4j.LoggerFactory

class Weather {
    private val log = LoggerFactory.getLogger(javaClass)
    private var currentWeather = WeatherType.SUNNY
    private val observers = mutableListOf<WeatherObserver>()
    fun addObserver(obs: WeatherObserver) {
        observers.add(obs)
    }

    fun removeObserver(obs: WeatherObserver) {
        observers.remove(obs)
    }

    fun timePasses() {
        val enumValues = WeatherType.values()
        currentWeather = enumValues[(currentWeather.ordinal + 1) % enumValues.size]
        log.info("The weather changed to {}.", currentWeather)
        observers.forEach { it.update(currentWeather) }
    }
}