interface CalculatorAction {
    fun tag(): String
}

object AdditionCalculatorAction : CalculatorAction {
    const val TAG: String = "ADDITION"
    override fun tag(): String = TAG
}

object SubtractionCalculatorAction : CalculatorAction {
    const val TAG: String = "SUBTRACTION"
    override fun tag(): String = TAG
}

object MultiplicationCalculatorAction : CalculatorAction {
    const val TAG: String = "MULTIPLICATION"
    override fun tag(): String = TAG
}

object DivisionCalculatorAction : CalculatorAction {
    const val TAG: String = "DIVISION"
    override fun tag(): String = TAG
}

class SetVariableCalculatorAction(val variable: Double) : CalculatorAction {
    companion object {
        const val TAG: String = "SET_VARIABLE"
    }

    override fun tag(): String = TAG
}
