interface Weapon {
    val weaponType: WeaponType
}

class ElfWeapon(override val weaponType: WeaponType) : Weapon {
    override fun toString(): String = "an elven $weaponType"
}

class OrcWeapon(override val weaponType: WeaponType) : Weapon {
    override fun toString(): String = "an orcish $weaponType"
}