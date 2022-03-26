import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RoyaltyObjectMotherTest {
    @Test
    fun `unsuccessful king flirt`() {
        val soberUnhappyKing = RoyaltyObjectMother.createSoberUnhappyKing()
        soberUnhappyKing.flirt(RoyaltyObjectMother.createFlirtyQueen())
        assertFalse(soberUnhappyKing.isHappy)
    }

    @Test
    fun `queen is blocking flirt cause drunk king`() {
        val drunkUnhappyKing = RoyaltyObjectMother.createDrunkKing()
        drunkUnhappyKing.flirt(RoyaltyObjectMother.createNotFlirtyQueen())
        assertFalse(drunkUnhappyKing.isHappy)
    }

    @Test
    fun `queen is blocking flirt`() {
        val soberHappyKing = RoyaltyObjectMother.createHappyKing()
        soberHappyKing.flirt(RoyaltyObjectMother.createNotFlirtyQueen())
        assertFalse(soberHappyKing.isHappy)
    }

    @Test
    fun `successful king flirt`() {
        val soberHappyKing = RoyaltyObjectMother.createHappyKing()
        soberHappyKing.flirt(RoyaltyObjectMother.createFlirtyQueen())
        assertTrue(soberHappyKing.isHappy)
    }

    @Test
    fun `queen type`() {
        assertEquals(RoyaltyObjectMother.createFlirtyQueen().javaClass, Queen::class.java)
        assertEquals(RoyaltyObjectMother.createNotFlirtyQueen().javaClass, Queen::class.java)
    }

    @Test
    fun `king type`() {
        assertEquals(RoyaltyObjectMother.createDrunkKing().javaClass, King::class.java)
        assertEquals(RoyaltyObjectMother.createHappyDrunkKing().javaClass, King::class.java)
        assertEquals(RoyaltyObjectMother.createHappyKing().javaClass, King::class.java)
        assertEquals(RoyaltyObjectMother.createSoberUnhappyKing().javaClass, King::class.java)
    }
}