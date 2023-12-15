package layers

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Runner(private val cakeBakingService: CakeBakingService) : CommandLineRunner {
    override fun run(vararg args: String) {
        initializeData()
        val cakeView = CakeView(cakeBakingService)
        cakeView.render()
    }

    private fun initializeData() {
        with(cakeBakingService) {
            saveNewLayer(CakeLayerInfo(name = "chocolate", calories = 1200))
            saveNewLayer(CakeLayerInfo(name = "banana", calories = 900))
            saveNewLayer(CakeLayerInfo(name = STRAWBERRY, calories = 950))
            saveNewLayer(CakeLayerInfo(name = "lemon", calories = 950))
            saveNewLayer(CakeLayerInfo(name = "vanilla", calories = 950))
            saveNewLayer(CakeLayerInfo(name = STRAWBERRY, calories = 950))
            saveNewTopping(CakeToppingInfo(name = "candies", calories = 350))
            saveNewTopping(CakeToppingInfo(name = "cherry", calories = 350))
            bakeNewCake(
                CakeInfo(
                    cakeToppingInfo = CakeToppingInfo(name = "candies", calories = 0),
                    cakeLayerInfos = listOf(
                        CakeLayerInfo(name = "chocolate", calories = 0),
                        CakeLayerInfo(name = "banana", calories = 0),
                        CakeLayerInfo(name = STRAWBERRY, calories = 0)
                    )
                )
            )
            bakeNewCake(
                CakeInfo(
                    cakeToppingInfo = CakeToppingInfo(name = "cherry", calories = 0),
                    cakeLayerInfos = listOf(
                        CakeLayerInfo(name = "vanilla", calories = 0),
                        CakeLayerInfo(name = "lemon", calories = 0),
                        CakeLayerInfo(name = STRAWBERRY, calories = 0)
                    )
                )
            )
        }
    }

    companion object {
        const val STRAWBERRY: String = "strawberry"
    }
}
