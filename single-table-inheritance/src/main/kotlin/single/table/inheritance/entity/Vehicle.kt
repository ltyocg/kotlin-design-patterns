package single.table.inheritance.entity

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE")
abstract class Vehicle
protected constructor(
    val manufacturer: String,
    val model: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var vehicleId = 0
    override fun toString(): String = "Vehicle{vehicleId=$vehicleId, manufacturer='$manufacturer', model='$model}"
}
