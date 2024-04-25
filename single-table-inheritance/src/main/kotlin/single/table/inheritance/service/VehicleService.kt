package single.table.inheritance.service

import org.springframework.stereotype.Service
import single.table.inheritance.entity.Vehicle
import single.table.inheritance.repository.VehicleRepository

@Service
class VehicleService(private val vehicleRepository: VehicleRepository) {
    fun saveVehicle(vehicle: Vehicle): Vehicle = vehicleRepository.save(vehicle)
    fun getVehicle(vehicleId: Int): Vehicle = vehicleRepository.findById(vehicleId).orElse(null)
    val allVehicles: List<Any>
        get() = vehicleRepository.findAll()

    fun updateVehicle(vehicle: Vehicle): Vehicle = vehicleRepository.save(vehicle)
    fun deleteVehicle(vehicle: Vehicle) = vehicleRepository.delete(vehicle)
}
