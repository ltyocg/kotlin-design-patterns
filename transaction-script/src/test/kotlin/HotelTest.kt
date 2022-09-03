import kotlin.test.*

class HotelTest {
    private companion object {
        private const val nonExistingRoomId = 999
    }

    private lateinit var hotel: Hotel
    private lateinit var dao: HotelDao

    @BeforeTest
    fun setUp() {
        val dataSource = createDataSource()
        deleteSchema(dataSource)
        createSchema(dataSource)
        dao = HotelDao(dataSource)
        dao.addRooms()
        hotel = Hotel(dao)
    }

    @Test
    fun `booking room should change booked status to true`() {
        hotel.bookRoom(1)
        assertTrue(dao.getById(1)!!.booked)
    }

    @Test
    fun `booking room with invalid id should raise exception`() {
        assertFailsWith<IllegalStateException> {
            hotel.bookRoom(nonExistingRoomId)
        }
    }

    @Test
    fun `booking room again should raise exception`() {
        assertFailsWith<IllegalStateException> {
            hotel.bookRoom(1)
            hotel.bookRoom(1)
        }
    }

    @Test
    fun `not booking room should not change booked status`() = assertFalse(dao.getById(1)!!.booked)

    @Test
    fun `cancel room booking should change booked status`() {
        hotel.bookRoom(1)
        assertTrue(dao.getById(1)!!.booked)
        hotel.cancelRoomBooking(1)
        assertFalse(dao.getById(1)!!.booked)
    }

    @Test
    fun `cancel room booking with invalid id should raise exception`() {
        assertFailsWith<IllegalStateException> {
            hotel.cancelRoomBooking(nonExistingRoomId)
        }
    }

    @Test
    fun `cancel room booking for unbooked room should raise exception`() {
        assertFailsWith<IllegalStateException> {
            hotel.cancelRoomBooking(1)
        }
    }
}