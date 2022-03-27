package generic

class OrcsTest : ObserverTest<GOrcs>({ GOrcs() }) {
    override fun dataProvider(): Collection<Array<Any>> = listOf(
        arrayOf(WeatherType.SUNNY, "The orcs are facing Sunny weather now"),
        arrayOf(WeatherType.RAINY, "The orcs are facing Rainy weather now"),
        arrayOf(WeatherType.WINDY, "The orcs are facing Windy weather now"),
        arrayOf(WeatherType.COLD, "The orcs are facing Cold weather now")
    )
}