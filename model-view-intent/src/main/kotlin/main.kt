private const val RANDOM_VARIABLE = 10.0
fun main() {
    val view = CalculatorView(CalculatorViewModel())
    var variable1 = RANDOM_VARIABLE
    view.setVariable(variable1)
    view.add()
    view.displayTotal()
    variable1 = 2.0
    view.setVariable(variable1)
    view.subtract()
    view.divide()
    view.multiply()
    view.displayTotal()
}
