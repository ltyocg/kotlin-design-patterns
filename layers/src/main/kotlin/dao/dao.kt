package dao

import Cake
import CakeLayer
import CakeTopping
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CakeDao : CrudRepository<Cake, Long>

@Repository
interface CakeLayerDao : CrudRepository<CakeLayer, Long>

@Repository
interface CakeToppingDao : CrudRepository<CakeTopping, Long>