package layers

import layers.dao.CakeDao
import layers.dao.CakeLayerDao
import layers.dao.CakeToppingDao
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CakeBakingService(
    private val cakeDao: CakeDao,
    private val cakeLayerDao: CakeLayerDao,
    private val cakeToppingDao: CakeToppingDao
) {
    fun bakeNewCake(cakeInfo: CakeInfo) {
        val matchingToppings = cakeToppingDao.findAll().filter { it.cake == null && it.name == cakeInfo.cakeToppingInfo.name }
        if (matchingToppings.isEmpty()) throw CakeBakingException("Topping ${cakeInfo.cakeToppingInfo.name} is not available")
        val allLayers = cakeLayerDao.findAll().filter { it.cake == null }
        val foundLayers = cakeInfo.cakeLayerInfos.map { info ->
            allLayers.firstOrNull { it.name == info.name } ?: throw CakeBakingException("Layer ${info.name} is not available")
        }.toMutableSet()
        val toppingOptional = cakeToppingDao.findById(matchingToppings[0].id)
        if (toppingOptional.isPresent) {
            val topping = toppingOptional.get()
            val cake = Cake(topping = topping, layers = foundLayers)
            cakeDao.save(cake)
            topping.cake = cake
            cakeToppingDao.save(topping)
            foundLayers.forEach { cakeLayerDao.save(it.apply { this.cake = cake }) }
        } else throw CakeBakingException("Topping ${cakeInfo.cakeToppingInfo.name} is not available")
    }

    fun saveNewTopping(toppingInfo: CakeToppingInfo) {
        cakeToppingDao.save(CakeTopping(name = toppingInfo.name, calories = toppingInfo.calories))
    }

    fun saveNewLayer(layerInfo: CakeLayerInfo) {
        cakeLayerDao.save(CakeLayer(name = layerInfo.name, calories = layerInfo.calories))
    }

    fun getAvailableToppings(): List<CakeToppingInfo> = cakeToppingDao.findAll()
        .filter { it.cake == null }
        .map { CakeToppingInfo(it.id, it.name, it.calories) }

    fun getAvailableLayers(): List<CakeLayerInfo> = cakeLayerDao.findAll()
        .filter { it.cake == null }
        .map { CakeLayerInfo(it.id, it.name, it.calories) }

    fun deleteAllCakes() = cakeDao.deleteAll()
    fun deleteAllLayers() = cakeLayerDao.deleteAll()
    fun deleteAllToppings() = cakeToppingDao.deleteAll()
    fun getAllCakes(): List<CakeInfo> = cakeDao.findAll().map { cake ->
        CakeInfo(
            cake.id,
            CakeToppingInfo(cake.topping!!.id, cake.topping!!.name, cake.topping!!.calories),
            cake.layers.map { CakeLayerInfo(it.id, it.name, it.calories) }
        )
    }
}
