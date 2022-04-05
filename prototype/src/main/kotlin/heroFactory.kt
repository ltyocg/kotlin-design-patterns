interface HeroFactory {
    fun createMage(): Mage
    fun createWarlord(): Warlord
    fun createBeast(): Beast
}

class HeroFactoryImpl(
    private val mage: Mage,
    private val warlord: Warlord,
    private val beast: Beast
) : HeroFactory {
    override fun createMage(): Mage = mage.copy()
    override fun createWarlord(): Warlord = warlord.copy()
    override fun createBeast(): Beast = beast.copy()
}