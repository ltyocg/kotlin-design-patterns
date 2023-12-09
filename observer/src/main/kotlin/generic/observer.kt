package generic

import WeatherType
import io.github.oshai.kotlinlogging.KotlinLogging

interface Observer<S : Observable<S, O, A>, O : Observer<S, O, A>, A> {
    fun update(subject: S?, argument: A)
}

interface Race : Observer<GWeather, Race, WeatherType>
class GHobbits : Race {
    private val logger = KotlinLogging.logger {}
    override fun update(subject: GWeather?, argument: WeatherType) =
        logger.info { "The hobbits are facing ${argument.description} weather now" }
}

class GOrcs : Race {
    private val logger = KotlinLogging.logger {}
    override fun update(subject: GWeather?, argument: WeatherType) =
        logger.info { "The orcs are facing ${argument.description} weather now" }
}
