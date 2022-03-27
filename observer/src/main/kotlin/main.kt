import generic.GHobbits
import generic.GOrcs
import generic.GWeather
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val weather = Weather()
    weather.addObserver(Orcs())
    weather.addObserver(Hobbits())
    repeat(4) { weather.timePasses() }
    log.info("--Running generic version--")
    val genericWeather = GWeather()
    genericWeather.addObserver(GOrcs())
    genericWeather.addObserver(GHobbits())
    repeat(4) { genericWeather.timePasses() }
}