import java.util.*

fun interface WeaponFactory {
    fun create(name: WeaponType): Weapon

    companion object {
        fun factory(consumer: (Builder) -> Unit): WeaponFactory {
            val map = EnumMap<WeaponType, () -> Weapon>(WeaponType::class.java)
            consumer(map::put)
            return WeaponFactory { map[it]!!.invoke() }
        }
    }
}