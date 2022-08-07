import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object RainbowFishSerializer {
    private const val LENGTH_METERS = "lengthMeters"
    private const val WEIGHT_TONS = "weightTons"
    fun writeV1(rainbowFish: RainbowFish, filename: String) = FileOutputStream(filename).use { fileOut ->
        ObjectOutputStream(fileOut).use {
            it.writeObject(buildMap {
                put("name", rainbowFish.name)
                put("age", rainbowFish.age.toString())
                put(LENGTH_METERS, rainbowFish.lengthMeters.toString())
                put(WEIGHT_TONS, rainbowFish.weightTons.toString())
            })
        }
    }

    fun writeV2(rainbowFish: RainbowFishV2, filename: String) = FileOutputStream(filename).use { fileOut ->
        ObjectOutputStream(fileOut).use {
            it.writeObject(buildMap {
                put("name", rainbowFish.name)
                put("age", rainbowFish.age.toString())
                put(LENGTH_METERS, rainbowFish.lengthMeters.toString())
                put(WEIGHT_TONS, rainbowFish.weightTons.toString())
                put("angry", rainbowFish.angry)
                put("hungry", rainbowFish.hungry)
                put("sleeping", rainbowFish.sleeping)
            })
        }
    }

    fun readV1(filename: String): RainbowFish = FileInputStream(filename).use { fileIn ->
        ObjectInputStream(fileIn).use {
            val map = it.readObject() as Map<String, String>
            RainbowFish(
                map["name"]!!,
                map["age"]!!.toInt(),
                map[LENGTH_METERS]!!.toInt(),
                map[WEIGHT_TONS]!!.toInt()
            )
        }
    }
}