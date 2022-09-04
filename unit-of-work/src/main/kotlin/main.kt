fun main() {
    with(ArmsDealer(mutableMapOf(), WeaponDatabase())) {
        registerNew(Weapon(1, "enchanted hammer"))
        registerModified(Weapon(3, "silver trident"))
        registerDeleted(Weapon(2, "broken great sword"))
        commit()
    }
}