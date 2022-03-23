import java.util.concurrent.ConcurrentHashMap

class Nazgul private constructor(val name: NazgulName) {

    companion object {
        private val nazguls = ConcurrentHashMap<NazgulName, Nazgul>().apply {
            NazgulName.values().forEach { put(it, Nazgul(it)) }
        }

        fun getInstance(name: NazgulName): Nazgul = nazguls[name]!!
    }
}

enum class NazgulEnum {
    KHAMUL, MURAZOR, DWAR, JI_INDUR, AKHORAHIL, HOARMURATH, ADUNAPHEL, REN, UVATHA
}

enum class NazgulName {
    KHAMUL, MURAZOR, DWAR, JI_INDUR, AKHORAHIL, HOARMURATH, ADUNAPHEL, REN, UVATHA
}
