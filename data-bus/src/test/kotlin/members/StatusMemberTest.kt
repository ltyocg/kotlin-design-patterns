package members

import DataBus
import MessageData
import StartingData
import StatusMember
import StoppingData
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StatusMemberTest {
    @Test
    fun `status records the start time`() {
        val startTime = LocalDateTime.of(2017, Month.APRIL, 1, 19, 9)
        val statusMember = StatusMember(1)
        statusMember(StartingData(startTime))
        assertEquals(startTime, statusMember.started)
    }

    @Test
    fun statusRecordsTheStopTime() {
        val stop = LocalDateTime.of(2017, Month.APRIL, 1, 19, 12)
        val stoppingData = StoppingData(stop)
        stoppingData.dataBus = DataBus
        val statusMember = StatusMember(1)
        statusMember(stoppingData)
        assertEquals(stop, statusMember.stopped)
    }

    @Test
    fun statusIgnoresMessageData() {
        val statusMember = StatusMember(1)
        statusMember(MessageData("message"))
        assertNull(statusMember.started)
        assertNull(statusMember.stopped)
    }
}