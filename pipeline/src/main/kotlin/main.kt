fun main() {
    Pipeline(RemoveAlphabetsHandler())
        .addHandler(RemoveDigitsHandler())
        .addHandler(ConvertToCharArrayHandler())
        .execute("GoYankees123!")
}