import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class RoomTest {
    private companion object {
        private const val ID = 1
        private const val ROOMTYPE = "Single"
        private const val PRICE = 50
        private const val BOOKED = false
    }

    private lateinit var room: Room

    @BeforeTest
    fun setUp() {
        room = Room(ID, ROOMTYPE, PRICE, BOOKED)
    }

    @Test
    fun `get and set id`() {
        val newId = 2
        room.id = newId
        assertEquals(newId, room.id)
    }

    @Test
    fun `get and set roomType`() {
        val newRoomType = "Double"
        room.roomType = newRoomType
        assertEquals(newRoomType, room.roomType)
    }

    @Test
    fun `get and set price`() {
        val newPrice = 60
        room.price = newPrice
        assertEquals(newPrice, room.price)
    }

    @Test
    fun `not equal with different id`() {
        val newId = 2
        val otherRoom = Room(newId, ROOMTYPE, PRICE, BOOKED)
        assertNotEquals(room, otherRoom)
        assertNotEquals(room.hashCode(), otherRoom.hashCode())
    }

    @Test
    fun `equals with same object values`() {
        val otherRoom = Room(ID, ROOMTYPE, PRICE, BOOKED)
        assertEquals(room, otherRoom)
        assertEquals(room.hashCode(), otherRoom.hashCode())
    }

    @Test
    fun `equals with same objects`() {
        assertEquals(room, room)
        assertEquals(room.hashCode(), room.hashCode())
    }

    @Test
    fun `test toString`() = assertEquals("Room(id=${room.id}, roomType=${room.roomType}, price=${room.price}, booked=${room.booked})", room.toString())
}