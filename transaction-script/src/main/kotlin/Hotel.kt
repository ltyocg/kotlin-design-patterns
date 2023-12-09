import io.github.oshai.kotlinlogging.KotlinLogging

class Hotel(private val hotelDao: HotelDao) {
    private val logger = KotlinLogging.logger {}
    fun bookRoom(roomNumber: Int) {
        val room = hotelDao.getById(roomNumber) ?: error("Room number: $roomNumber does not exist")
        if (room.booked) error("Room already booked!")
        hotelDao.update(room.apply { booked = true })
    }

    fun cancelRoomBooking(roomNumber: Int) {
        val room = hotelDao.getById(roomNumber) ?: error("Room number: $roomNumber does not exist")
        if (!room.booked) error("No booking for the room exists")
        hotelDao.update(room.apply { booked = false })
        logger.info { "Booking cancelled for room number: $roomNumber" }
        logger.info { "${room.price} is refunded" }
    }
}
