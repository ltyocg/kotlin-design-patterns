fun main() {
    with(FrontController()) {
        handleRequest("Archer")
        handleRequest("Catapult")
        handleRequest("foobar")
    }
}