import org.h2.jdbcx.JdbcDataSource
import org.slf4j.LoggerFactory
import javax.sql.DataSource

private val log = LoggerFactory.getLogger("main")
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

fun createDataSource(): DataSource = JdbcDataSource().apply { setUrl("jdbc:h2:~/test") }
fun deleteSchema(dataSource: DataSource) = dataSource.execute(RoomSchemaSql.DELETE_SCHEMA_SQL)
fun createSchema(dataSource: DataSource) = dataSource.execute(RoomSchemaSql.CREATE_SCHEMA_SQL)
private fun HotelDao.getRoomStatus() = all.map(Room::toString).forEach(log::info)
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