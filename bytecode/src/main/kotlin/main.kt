fun main() {
    with(
        VirtualMachine(
            Wizard(45, 7, 11, 0, 0),
            Wizard(36, 18, 8, 0, 0)
        )
    ) {
        execute("LITERAL 0")
        execute("LITERAL 0")
        execute("GET_HEALTH")
        execute("LITERAL 0")
        execute("GET_AGILITY")
        execute("LITERAL 0")
        execute("GET_WISDOM")
        execute("ADD")
        execute("LITERAL 2")
        execute("DIVIDE")
        execute("ADD")
        execute("SET_HEALTH")
    }
}

private fun VirtualMachine.execute(instructions: String) {
    execute(convertToByteCode(instructions))
}