import io.github.oshai.kotlinlogging.KotlinLogging

interface Fighter<T> {
    fun fight(t: T)
}

open class MmaFighter<T : MmaFighter<T>>(
    private val name: String,
    private val surname: String,
    private val nickName: String,
    private val speciality: String
) : Fighter<T> {
    private val logger = KotlinLogging.logger {}
    override fun fight(t: T) = logger.info { "$this is going to fight against $t" }
}

class MmaBantamweightFighter(
    name: String,
    surname: String,
    nickName: String,
    speciality: String
) : MmaFighter<MmaBantamweightFighter>(name, surname, nickName, speciality)

class MmaHeavyweightFighter(
    name: String,
    surname: String,
    nickName: String,
    speciality: String
) : MmaFighter<MmaHeavyweightFighter>(name, surname, nickName, speciality)

class MmaLightweightFighter(
    name: String,
    surname: String,
    nickName: String,
    speciality: String
) : MmaFighter<MmaLightweightFighter>(name, surname, nickName, speciality)
