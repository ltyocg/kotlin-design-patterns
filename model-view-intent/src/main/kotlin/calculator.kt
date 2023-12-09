import io.github.oshai.kotlinlogging.KotlinLogging

data class CalculatorModel(
    val variable: Double,
    val output: Double
)

data class CalculatorView(private val viewModel: CalculatorViewModel) {
    private val logger = KotlinLogging.logger {}
    fun displayTotal() = logger.info { "Total value = ${viewModel.calculatorModel.output}" }
    fun add() = viewModel.handleAction(AdditionCalculatorAction)
    fun subtract() = viewModel.handleAction(SubtractionCalculatorAction)
    fun multiply() = viewModel.handleAction(MultiplicationCalculatorAction)
    fun divide() = viewModel.handleAction(DivisionCalculatorAction)
    fun setVariable(value: Double) = viewModel.handleAction(SetVariableCalculatorAction(value))
}

class CalculatorViewModel {
    var calculatorModel: CalculatorModel = CalculatorModel(0.0, 0.0)
        private set

    fun handleAction(action: CalculatorAction) {
        calculatorModel = when (action.tag()) {
            AdditionCalculatorAction.TAG -> calculatorModel.copy(output = calculatorModel.output + calculatorModel.variable)
            SubtractionCalculatorAction.TAG -> calculatorModel.copy(output = calculatorModel.output - calculatorModel.variable)
            MultiplicationCalculatorAction.TAG -> calculatorModel.copy(output = calculatorModel.output * calculatorModel.variable)
            DivisionCalculatorAction.TAG -> calculatorModel.copy(output = calculatorModel.output / calculatorModel.variable)
            SetVariableCalculatorAction.TAG -> calculatorModel.copy(variable = (action as SetVariableCalculatorAction).variable)
            else -> return
        }
    }
}
