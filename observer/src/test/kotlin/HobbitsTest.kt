class HobbitsTest : WeatherObserverTest<Hobbits>({ Hobbits() }) {
    override fun dataProvider(): Collection<Array<Any>> = listOf(
        arrayOf(WeatherType.SUNNY, "The hobbits are facing Sunny weather now"),
        arrayOf(WeatherType.RAINY, "The hobbits are facing Rainy weather now"),
        arrayOf(WeatherType.WINDY, "The hobbits are facing Windy weather now"),
        arrayOf(WeatherType.COLD, "The hobbits are facing Cold weather now")
    )
}