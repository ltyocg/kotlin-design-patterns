import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class Candy(
    var name: String,
    @SerialName("parent") var parentName: String?,
    val type: Type,
    var points: Int
) {
    @Serializable
    enum class Type {
        @SerialName("crushableCandy")
        CRUSHABLE_CANDY,

        @SerialName("rewardFruit")
        REWARD_FRUIT
    }

    @Transient
    var parent: Candy? = null
}