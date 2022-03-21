import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class GiantControllerTest {
    @Test
    fun `set health`() {
        val (model, view, controller) = initParameters()
        verifyNoMoreInteractions(model, view)
        Health.values().forEach {
            controller.health = it
            verify(model).health = it
            verifyNoMoreInteractions(view)
        }
        controller.health
        verify(model).health
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun `set fatigue`() {
        val (model, view, controller) = initParameters()
        verifyNoMoreInteractions(model, view)
        Fatigue.values().forEach {
            controller.fatigue = it
            verify(model).fatigue = it
            verifyNoMoreInteractions(view)
        }
        controller.fatigue
        verify(model).fatigue
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun `set nourishment`() {
        val (model, view, controller) = initParameters()
        verifyNoMoreInteractions(model, view)
        Nourishment.values().forEach {
            controller.nourishment = it
            verify(model).nourishment = it
            verifyNoMoreInteractions(view)
        }
        controller.nourishment
        verify(model).nourishment
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun `update view`() {
        val (model, view, controller) = initParameters()
        verifyNoMoreInteractions(model, view)
        controller.updateView()
        verify(view).displayGiant(model)
        verifyNoMoreInteractions(model, view)
    }

    private fun initParameters(): InitParameters = InitParameters()
    private data class InitParameters(
        val model: GiantModel = mock(),
        val view: GiantView = mock(),
        val controller: GiantController = GiantController(model, view)
    )
}