import org.slf4j.LoggerFactory

class AlchemistShop {
    private val log = LoggerFactory.getLogger(javaClass)
    private val _topShelf: List<Potion>
    private val _bottomShelf: List<Potion>

    init {
        val factory = PotionFactory()
        _topShelf = listOf(
            PotionType.INVISIBILITY,
            PotionType.INVISIBILITY,
            PotionType.STRENGTH,
            PotionType.HEALING,
            PotionType.INVISIBILITY,
            PotionType.STRENGTH,
            PotionType.HEALING,
            PotionType.HEALING
        ).map(factory::createPotion)
        _bottomShelf = listOf(
            PotionType.POISON,
            PotionType.POISON,
            PotionType.POISON,
            PotionType.HOLY_WATER,
            PotionType.HOLY_WATER
        ).map(factory::createPotion)
    }

    val topShelf: List<Potion>
        get() = _topShelf.toList()
    val bottomShelf: List<Potion>
        get() = _bottomShelf.toList()

    fun drinkPotions() {
        log.info("Drinking top shelf potions")
        _topShelf.forEach(Potion::drink)
        log.info("Drinking bottom shelf potions")
        _bottomShelf.forEach(Potion::drink)
    }
}