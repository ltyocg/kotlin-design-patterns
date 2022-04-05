fun main() {
    with(
        BusinessLookup(NetflixService(), YouTubeService())
            .let(::BusinessDelegate)
            .let(::MobileClient)
    ) {
        playbackMovie("Die Hard 2")
        playbackMovie("Maradona: The Greatest Ever")
    }
}