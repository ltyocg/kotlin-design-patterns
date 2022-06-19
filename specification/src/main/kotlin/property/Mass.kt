package property

data class Mass(private val value: Double) : Comparable<Mass> {
    private val title = "${value}kg"
    override operator fun compareTo(other: Mass): Int = value.compareTo(other.value)
    override fun toString(): String = title
}