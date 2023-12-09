import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val fishV1 = RainbowFish("Zed", 10, 11, 12)
    logger.info { "fishV1 $fishV1" }
    RainbowFishSerializer.writeV1(fishV1, "fish1.out")
    val deserializedRainbowFishV1 = RainbowFishSerializer.readV1("fish1.out")
    logger.info { "deserializedFishV1 $deserializedRainbowFishV1" }
    val fishV2 = RainbowFishV2("Scar", 5, 12, 15, sleeping = true, hungry = true, angry = true)
    logger.info { "fishV2 $fishV2" }
    RainbowFishSerializer.writeV2(fishV2, "fish2.out")
    val deserializedFishV2 = RainbowFishSerializer.readV1("fish2.out")
    logger.info { "deserializedFishV2 $deserializedFishV2" }
}
