fun interface Builder {
    fun add(name: WeaponType, supplier: () -> Weapon)
}