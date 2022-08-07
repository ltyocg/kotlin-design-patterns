import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val fishV1 = RainbowFish("Zed", 10, 11, 12)
    log.info(
        "fishV1 name={} age={} length={} weight={}",
        fishV1.name,
        fishV1.age,
        fishV1.lengthMeters,
        fishV1.weightTons
    )
    RainbowFishSerializer.writeV1(fishV1, "fish1.out")
    val deserializedRainbowFishV1 = RainbowFishSerializer.readV1("fish1.out")
    log.info(
        "deserializedFishV1 name={} age={} length={} weight={}",
        deserializedRainbowFishV1.name,
        deserializedRainbowFishV1.age,
        deserializedRainbowFishV1.lengthMeters,
        deserializedRainbowFishV1.weightTons
    )
    val fishV2 = RainbowFishV2("Scar", 5, 12, 15, sleeping = true, hungry = true, angry = true)
    log.info(
        "fishV2 name={} age={} length={} weight={} sleeping={} hungry={} angry={}",
        fishV2.name,
        fishV2.age,
        fishV2.lengthMeters,
        fishV2.weightTons,
        fishV2.hungry,
        fishV2.angry,
        fishV2.sleeping
    )
    RainbowFishSerializer.writeV2(fishV2, "fish2.out")
    val deserializedFishV2 = RainbowFishSerializer.readV1("fish2.out")
    log.info(
        "deserializedFishV2 name={} age={} length={} weight={}",
        deserializedFishV2.name,
        deserializedFishV2.age,
        deserializedFishV2.lengthMeters,
        deserializedFishV2.weightTons
    )
}