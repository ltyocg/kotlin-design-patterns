import java.util.*

interface Blacksmith {
    fun manufactureWeapon(weaponType: WeaponType): Weapon
}

class ElfBlacksmith : Blacksmith {
    companion object {
        private val ELFARSENAL = EnumMap(WeaponType.values().associateWith(::ElfWeapon))
    }

    override fun manufactureWeapon(weaponType: WeaponType): Weapon = ELFARSENAL[weaponType]!!
    override fun toString(): String = "The elf blacksmith"
}

class OrcBlacksmith : Blacksmith {
    companion object {
        private val ORCARSENAL = EnumMap(WeaponType.values().associateWith(::OrcWeapon))
    }

    override fun manufactureWeapon(weaponType: WeaponType): Weapon = ORCARSENAL[weaponType]!!
    override fun toString(): String = "The orc blacksmith"
}