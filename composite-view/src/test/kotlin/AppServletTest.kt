import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.test.Test
import kotlin.test.assertContains

class AppServletTest : Mockito() {
    private val destination = "newsDisplay.jsp"

    @Test
    fun doGet() {
        val mockReq = mock<HttpServletRequest>()
        val mockResp = mock<HttpServletResponse>()
        val mockDispatcher = mock<RequestDispatcher>()
        whenever(mockResp.writer).thenReturn(PrintWriter(StringWriter()))
        whenever(mockReq.getRequestDispatcher(destination)).thenReturn(mockDispatcher)
        AppServlet().doGet(mockReq, mockResp)
        verify(mockReq, times(1)).getRequestDispatcher(destination)
        verify(mockDispatcher).forward(mockReq, mockResp)
    }

    @Test
    fun doPost() = requestContains(AppServlet::doPost, "Post")

    @Test
    fun doDelete() = requestContains(AppServlet::doDelete, "Delete")

    @Test
    fun doPut() = requestContains(AppServlet::doPut, "Put")

    private fun requestContains(action: AppServlet.(HttpServletRequest, HttpServletResponse) -> Unit, type: String) {
        val mockResp = mock<HttpServletResponse>()
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        whenever(mockResp.writer).thenReturn(printWriter)
        AppServlet().action(mock(), mockResp)
        printWriter.flush()
        assertContains(stringWriter.toString(), AppServlet.msg(type))
    }
}