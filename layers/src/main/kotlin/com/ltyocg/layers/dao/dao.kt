package com.ltyocg.layers.dao

import com.ltyocg.layers.Cake
import com.ltyocg.layers.CakeLayer
import com.ltyocg.layers.CakeTopping
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CakeDao : CrudRepository<Cake, Long>

@Repository
interface CakeLayerDao : CrudRepository<CakeLayer, Long>

@Repository
interface CakeToppingDao : CrudRepository<CakeTopping, Long>