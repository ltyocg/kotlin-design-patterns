data class Person(
    val personNationalId: Int,
    var name: String,
    var phoneNum: Long
) {
    override fun hashCode(): Int = personNationalId
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return personNationalId == (other as Person).personNationalId
    }

    override fun toString(): String = "Person ID is : $personNationalId ; Person Name is : $name ; Phone Number is :$phoneNum"
}
