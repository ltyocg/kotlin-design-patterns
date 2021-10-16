package com.ltyocg.flyweight

import java.util.*

class PotionFactory {
    private val potions = EnumMap<PotionType, Potion>(PotionType::class.java)
    fun createPotion(type: PotionType): Potion = potions.getOrPut(type) {
        when (type) {
            PotionType.HEALING -> HealingPotion()
            PotionType.HOLY_WATER -> HolyWaterPotion()
            PotionType.INVISIBILITY -> InvisibilityPotion()
            PotionType.POISON -> PoisonPotion()
            PotionType.STRENGTH -> StrengthPotion()
        }
    }
}