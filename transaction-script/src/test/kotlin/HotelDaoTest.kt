import org.junit.jupiter.api.Nested
import org.mockito.kotlin.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.sql.DataSource
import kotlin.test.*

class HotelDaoTest {
    companion object {
        private const val DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"
        private const val nonExistingRoomId = 999
    }

    private lateinit var dao: HotelDao
    private val existingRoom = Room(1, "Single", 50, false)

    @BeforeTest
    fun createSchema() {
        DriverManager.getConnection(DB_URL).use { connection ->
            connection.createStatement().use {
                it.execute(RoomSchemaSql.DELETE_SCHEMA_SQL)
                it.execute(RoomSchemaSql.CREATE_SCHEMA_SQL)
            }
        }
    }

    @Nested
    inner class ConnectionSuccess {
        @BeforeTest
        fun setUp() {
            val dataSource = createDataSource()
            dao = HotelDao(dataSource)
            val result = dao.add(existingRoom)
            assertTrue(result)
        }

        @Nested
        inner class NonExistingRoom {
            @Test
            fun `adding should result in success`() {
                assertRoomCountIs(1)
                val nonExistingRoom = Room(2, "Double", 80, false)
                assertTrue(dao.add(nonExistingRoom))
                assertRoomCountIs(2)
                assertEquals(nonExistingRoom, dao.getById(nonExistingRoom.id))
            }

            @Test
            fun `deletion should be failure and not affect existing rooms`() {
                assertFalse(dao.delete(Room(2, "Double", 80, false)))
                assertRoomCountIs(1)
            }

            @Test
            fun `update should be failure and not affect existing rooms`() {
                assertFalse(dao.update(Room(nonExistingRoomId, "Double", 80, false)))
                assertNull(dao.getById(nonExistingRoomId))
            }

            @Test
            fun `retrieve should return no room`() = assertNull(dao.getById(nonExistingRoomId))
        }

        @Nested
        inner class ExistingRoom {
            @Test
            fun `adding should result in failure and not affect existing rooms`() {
                val existingRoom = Room(1, "Single", 50, false)
                assertFalse(dao.add(existingRoom))
                assertRoomCountIs(1)
                assertEquals(existingRoom, dao.getById(existingRoom.id))
            }

            @Test
            fun `deletion should be success and room should be non accessible`() {
                assertTrue(dao.delete(existingRoom))
                assertRoomCountIs(0)
                assertNull(dao.getById(existingRoom.id))
            }

            @Test
            fun `update should be success and accessing the same room should return updated information`() {
                val newRoomType = "Double"
                val newPrice = 80
                val newBookingStatus = false
                assertTrue(dao.update(Room(existingRoom.id, newRoomType, newPrice, newBookingStatus)))
                val room = dao.getById(existingRoom.id)!!
                assertEquals(newRoomType, room.roomType)
                assertEquals(newPrice, room.price)
                assertEquals(newBookingStatus, room.booked)
            }
        }
    }

    @Nested
    inner class ConnectivityIssue {
        @BeforeTest
        fun setUp() {
            val mockedDataSource = mock<DataSource>()
            val mockedConnection = mock<Connection>()
            doThrow(SQLException("Connection not available")).whenever(mockedConnection).prepareStatement(any())
            doReturn(mockedConnection).whenever(mockedDataSource).connection
            dao = HotelDao(mockedDataSource)
        }

        @Test
        fun `adding a room fails with exception as feedback to client`() {
            assertFailsWith<SQLException> {
                dao.add(Room(2, "Double", 80, false))
            }
        }

        @Test
        fun `deleting a room fails with exception as feedback to the client`() {
            assertFailsWith<SQLException> {
                dao.delete(existingRoom)
            }
        }

        @Test
        fun `update a room fails with feedback to the client`() {
            assertFailsWith<SQLException> {
                dao.update(Room(existingRoom.id, "Double", 80, false))
            }
        }

        @Test
        fun `retrieving a room by id fails with exception as feedback to client`() {
            assertFailsWith<SQLException> {
                dao.getById(existingRoom.id)
            }
        }

        @Test
        fun `retrieving all rooms fails with exception as feedback to client`() {
            assertFailsWith<SQLException> {
                dao.all
            }
        }
    }

    @AfterTest
    fun deleteSchema() {
        DriverManager.getConnection(DB_URL).use { connection -> connection.createStatement().use { statement -> statement.execute(RoomSchemaSql.DELETE_SCHEMA_SQL) } }
    }

    private fun assertRoomCountIs(count: Int) = assertEquals(count, dao.all.count())
}
