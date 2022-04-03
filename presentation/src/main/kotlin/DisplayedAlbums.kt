class DisplayedAlbums {
    val albums = mutableListOf<Album>()
    fun addAlbums(title: String, artist: String, isClassical: Boolean, composer: String?) {
        if (isClassical) albums.add(Album(title, artist, true, composer))
        else albums.add(Album(title, artist, false, ""))
    }
}