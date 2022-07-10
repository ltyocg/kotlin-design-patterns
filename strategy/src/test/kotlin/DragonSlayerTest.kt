import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class DragonSlayerTest {
    @Test
    fun goToBattle() {
        val strategy = mock<DragonSlayingStrategy>()
        DragonSlayer(strategy).goToBattle()
        verify(strategy).execute()
        verifyNoMoreInteractions(strategy)
    }

    @Test
    fun changeStrategy() {
        val initialStrategy = mock<DragonSlayingStrategy>()
        val dragonSlayer = DragonSlayer(initialStrategy)
        dragonSlayer.goToBattle()
        verify(initialStrategy).execute()
        val newStrategy = mock<DragonSlayingStrategy>()
        dragonSlayer.changeStrategy(newStrategy)
        dragonSlayer.goToBattle()
        verify(newStrategy).execute()
        verifyNoMoreInteractions(initialStrategy, newStrategy)
    }
}