import generic.GHobbits
import generic.GOrcs
import generic.GWeather
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val weather = Weather()
    weather.addObserver(Orcs())
    weather.addObserver(Hobbits())
    repeat(4) { weather.timePasses() }
    logger.info { "--Running generic version--" }
    val genericWeather = GWeather()
    genericWeather.addObserver(GOrcs())
    genericWeather.addObserver(GHobbits())
    repeat(4) { genericWeather.timePasses() }
}
