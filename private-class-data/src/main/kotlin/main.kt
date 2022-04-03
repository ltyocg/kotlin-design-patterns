fun main() {
    with(Stew(1, 2, 3, 4)) {
        mix()
        taste()
        mix()
    }
    ImmutableStew(2, 4, 3, 6).mix()
}