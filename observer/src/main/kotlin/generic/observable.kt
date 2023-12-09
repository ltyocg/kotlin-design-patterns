package generic

import WeatherType
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.CopyOnWriteArrayList

abstract class Observable<S : Observable<S, O, A>, O : Observer<S, O, A>, A> {
    private val observers = CopyOnWriteArrayList<O>()
    fun addObserver(observer: O) {
        observers.add(observer)
    }

    fun removeObserver(observer: O) {
        observers.remove(observer)
    }

    fun notifyObservers(argument: A) = observers.forEach {
        @Suppress("UNCHECKED_CAST")
        it.update(this as S, argument)
    }
}

class GWeather : Observable<GWeather, Race, WeatherType>() {
    private val logger = KotlinLogging.logger {}
    private var currentWeather = WeatherType.SUNNY
    fun timePasses() {
        val enumValues = WeatherType.entries
        currentWeather = enumValues[(currentWeather.ordinal + 1) % enumValues.size]
        logger.info { "The weather changed to $currentWeather." }
        notifyObservers(currentWeather)
    }
}
