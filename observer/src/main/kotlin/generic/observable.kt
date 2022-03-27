package generic

import WeatherType
import org.slf4j.LoggerFactory
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
        it.update(this as S, argument)
    }
}

class GWeather : Observable<GWeather, Race, WeatherType>() {
    private val log = LoggerFactory.getLogger(javaClass)
    private var currentWeather = WeatherType.SUNNY
    fun timePasses() {
        val enumValues = WeatherType.values()
        currentWeather = enumValues[(currentWeather.ordinal + 1) % enumValues.size]
        log.info("The weather changed to {}.", currentWeather)
        notifyObservers(currentWeather)
    }
}