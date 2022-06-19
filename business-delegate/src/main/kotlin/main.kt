fun main() {
    with(
        BusinessLookup()
            .let(::BusinessDelegate)
            .let(::MobileClient)
    ) {
        playbackMovie("Die Hard 2")
        playbackMovie("Maradona: The Greatest Ever")
    }
}