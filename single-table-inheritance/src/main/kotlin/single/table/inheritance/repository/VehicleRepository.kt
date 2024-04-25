package single.table.inheritance.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import single.table.inheritance.entity.Vehicle

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Int>
