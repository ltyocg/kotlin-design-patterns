import kotlin.test.Test

class CalculatorViewModelTest {
    private fun modelAfterExecutingActions(actions: List<CalculatorAction>): CalculatorModel {
        val viewModel = CalculatorViewModel()
        for (action in actions) viewModel.handleAction(action)
        return viewModel.calculatorModel
    }

    @Test
    fun setup() {
        val model = modelAfterExecutingActions(emptyList())
        assert(model.variable == 0.0 && model.output == 0.0)
    }

    @Test
    fun setVariable() {
        val model = modelAfterExecutingActions(listOf(SetVariableCalculatorAction(10.0)))
        assert(model.variable == 10.0 && model.output == 0.0)
    }

    @Test
    fun addition() {
        val model = modelAfterExecutingActions(
            listOf(
                SetVariableCalculatorAction(2.0),
                AdditionCalculatorAction,
                AdditionCalculatorAction,
                SetVariableCalculatorAction(7.0),
                AdditionCalculatorAction
            )
        )
        assert(model.variable == 7.0 && model.output == 11.0)
    }

    @Test
    fun subtraction() {
        val model = modelAfterExecutingActions(
            listOf(
                SetVariableCalculatorAction(2.0),
                AdditionCalculatorAction,
                AdditionCalculatorAction,
                SubtractionCalculatorAction
            )
        )
        assert(model.variable == 2.0 && model.output == 2.0)
    }

    @Test
    fun multiplication() {
        val model = modelAfterExecutingActions(
            listOf(
                SetVariableCalculatorAction(2.0),
                AdditionCalculatorAction,
                AdditionCalculatorAction,
                MultiplicationCalculatorAction
            )
        )
        assert(model.variable == 2.0 && model.output == 8.0)
    }

    @Test
    fun division() {
        val model = modelAfterExecutingActions(
            listOf(
                SetVariableCalculatorAction(2.0),
                AdditionCalculatorAction,
                AdditionCalculatorAction,
                SetVariableCalculatorAction(2.0),
                DivisionCalculatorAction
            )
        )
        assert(model.variable == 2.0 && model.output == 2.0)
    }
}
