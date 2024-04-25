package single.table.inheritance.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(value = "CAR")
class Car(
    manufacturer: String,
    model: String,
    noOfPassengers: Int,
    val engineCapacity: Int
) : PassengerVehicle(manufacturer, model, noOfPassengers) {
    override fun toString(): String = "Car{${super.toString()}}"
}
