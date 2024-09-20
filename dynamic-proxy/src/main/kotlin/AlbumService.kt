import tinyrestclient.*

interface AlbumService {
    @Get("/albums")
    fun readAlbums(): List<Album>

    @Get("/albums/{albumId}")
    fun readAlbum(@Path("albumId") albumId: Int): Album?

    @Post("/albums")
    fun createAlbum(@Body album: Album): Album?

    @Put("/albums/{albumId}")
    fun updateAlbum(@Path("albumId") albumId: Int, @Body album: Album): Album?

    @Delete("/albums/{albumId}")
    fun deleteAlbum(@Path("albumId") albumId: Int): Album?
}
