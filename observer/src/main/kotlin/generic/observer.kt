package generic

import WeatherType
import org.slf4j.LoggerFactory

interface Observer<S : Observable<S, O, A>, O : Observer<S, O, A>, A> {
    fun update(subject: S?, argument: A)
}

interface Race : Observer<GWeather, Race, WeatherType>
class GHobbits : Race {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update(subject: GWeather?, argument: WeatherType) {
        log.info("The hobbits are facing {} weather now", argument.description)
    }
}

class GOrcs : Race {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update(subject: GWeather?, argument: WeatherType) =
        log.info("The orcs are facing {} weather now", argument.description)
}