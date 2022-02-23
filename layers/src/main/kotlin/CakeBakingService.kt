import dao.CakeDao
import dao.CakeLayerDao
import dao.CakeToppingDao
import org.springframework.beans.factory.getBean
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CakeBakingService {
    private val context = ClassPathXmlApplicationContext("applicationContext.xml")
    private val cakeDao = context.getBean<CakeDao>()
    private val cakeLayerDao = context.getBean<CakeLayerDao>()
    private val cakeToppingDao = context.getBean<CakeToppingDao>()
    fun bakeNewCake(cakeInfo: CakeInfo) {
        val matchingToppings = cakeToppingDao.findAll().filter { it.cake == null && it.name == cakeInfo.cakeToppingInfo.name }
        if (matchingToppings.isEmpty()) throw CakeBakingException("Topping ${cakeInfo.cakeToppingInfo.name} is not available")
        val allLayers = context.getBean<CakeLayerDao>().findAll().filter { it.cake == null }
        val foundLayers = cakeInfo.cakeLayerInfos.map { info ->
            allLayers.firstOrNull { it.name == info.name } ?: throw CakeBakingException("Layer ${info.name} is not available")
        }.toMutableSet()
        val toppingOptional = cakeToppingDao.findById(matchingToppings[0].id)
        if (toppingOptional.isPresent) {
            val topping = toppingOptional.get()
            val cake = Cake().apply {
                this.topping = topping
                layers = foundLayers
            }
            cakeDao.save(cake)
            topping.cake = cake
            cakeToppingDao.save(topping)
            foundLayers.forEach { cakeLayerDao.save(it.apply { this.cake = cake }) }
        } else throw CakeBakingException("Topping ${cakeInfo.cakeToppingInfo.name} is not available")
    }

    fun saveNewTopping(toppingInfo: CakeToppingInfo) {
        cakeToppingDao.save(CakeTopping(toppingInfo.name, toppingInfo.calories))
    }

    fun saveNewLayer(layerInfo: CakeLayerInfo) {
        cakeLayerDao.save(CakeLayer(layerInfo.name, layerInfo.calories))
    }

    fun getAvailableToppings(): List<CakeToppingInfo> = cakeToppingDao.findAll().filter { it.cake == null }.map { CakeToppingInfo(it.id, it.name, it.calories) }
    fun getAvailableLayers(): List<CakeLayerInfo> = cakeLayerDao.findAll().filter { it.cake == null }.map { CakeLayerInfo(it.id, it.name, it.calories) }
    fun getAllCakes(): List<CakeInfo> = cakeDao.findAll().map { cake ->
        CakeInfo(cake.id, CakeToppingInfo(cake.topping!!.id, cake.topping!!.name, cake.topping!!.calories), cake.layers.map { CakeLayerInfo(it.id, it.name, it.calories) })
    }
}