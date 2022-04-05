class Star(
    private var type: StarType,
    private var ageYears: Int,
    private var massTons: Int
) {
    fun timePasses() {
        ageYears *= 2
        massTons *= 8
        when (type) {
            StarType.RED_GIANT -> type = StarType.WHITE_DWARF
            StarType.SUN -> type = StarType.RED_GIANT
            StarType.SUPERNOVA -> type = StarType.DEAD
            StarType.WHITE_DWARF -> type = StarType.SUPERNOVA
            StarType.DEAD -> {
                ageYears *= 2
                massTons = 0
            }
        }
    }

    var memento: StarMemento
        get() = StarMementoInternal(type, ageYears, massTons)
        set(memento) {
            val state = memento as StarMementoInternal
            type = state.type
            ageYears = state.ageYears
            massTons = state.massTons
        }

    override fun toString(): String = "$type age: $ageYears years mass: $massTons tons"
    private data class StarMementoInternal(val type: StarType, val ageYears: Int, val massTons: Int) : StarMemento
}

interface StarMemento