import org.slf4j.LoggerFactory

class Hotel(private val hotelDao: HotelDao) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun bookRoom(roomNumber: Int) {
        val room = hotelDao.getById(roomNumber) ?: error("Room number: $roomNumber does not exist")
        if (room.booked) error("Room already booked!")
        hotelDao.update(room.apply { booked = true })
    }

    fun cancelRoomBooking(roomNumber: Int) {
        val room = hotelDao.getById(roomNumber) ?: error("Room number: $roomNumber does not exist")
        if (!room.booked) error("No booking for the room exists")
        hotelDao.update(room.apply { booked = false })
        log.info("Booking cancelled for room number: {}", roomNumber)
        log.info("{} is refunded", room.price)
    }
}