fun main() {
    with(MobileClient(BusinessDelegate(BusinessLookup(NetflixService(), YouTubeService())))) {
        playbackMovie("Die Hard 2")
        playbackMovie("Maradona: The Greatest Ever")
    }
}