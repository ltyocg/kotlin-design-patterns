package single.table.inheritance.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(value = "FREIGHTER")
class Freighter(
    manufacturer: String,
    model: String,
    loadCapacity: Int,
    val flightLength: Double
) : TransportVehicle(manufacturer, model, loadCapacity) {
    override fun toString(): String = "Freighter{ ${super.toString()} ,flightLength=$flightLength}"
}
