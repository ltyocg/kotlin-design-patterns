import io.github.oshai.kotlinlogging.KotlinLogging
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}
fun main() {
    val dataSource = createDataSource()
    deleteSchema(dataSource)
    createSchema(dataSource)
    val dao = HotelDao(dataSource)
    dao.addRooms()
    dao.getRoomStatus()
    val hotel = Hotel(dao)
    hotel.bookRoom(1)
    hotel.bookRoom(2)
    hotel.bookRoom(3)
    hotel.bookRoom(4)
    hotel.bookRoom(5)
    hotel.bookRoom(6)
    hotel.cancelRoomBooking(1)
    hotel.cancelRoomBooking(3)
    hotel.cancelRoomBooking(5)
    dao.getRoomStatus()
    deleteSchema(dataSource)
}

fun createDataSource(): DataSource = JdbcDataSource().apply { setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1") }
fun deleteSchema(dataSource: DataSource) = dataSource.execute(RoomSchemaSql.DELETE_SCHEMA_SQL)
fun createSchema(dataSource: DataSource) = dataSource.execute(RoomSchemaSql.CREATE_SCHEMA_SQL)
private fun HotelDao.getRoomStatus() = all.forEach { logger.info { it } }
private fun DataSource.execute(sql: String) {
    connection.use { c -> c.createStatement().use { it.execute(sql) } }
}

fun HotelDao.addRooms() = sequenceOf(
    Room(1, "Single", 50, false),
    Room(2, "Double", 80, false),
    Room(3, "Queen", 120, false),
    Room(4, "King", 150, false),
    Room(5, "Single", 50, false),
    Room(6, "Double", 80, false)
).forEach(::add)
