package com.ltyocg.compositeview

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class AppServlet : HttpServlet() {
    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.setAttribute("properties", ClientPropertiesBean(req))
        req.getRequestDispatcher("newsDisplay.jsp").forward(req, resp)
    }

    public override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.writer.use { it.println(msg("Post")) }
    }

    public override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.writer.use { it.println(msg("Delete")) }
    }

    public override fun doPut(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.writer.use { it.println(msg("Put")) }
    }

    companion object {
        fun msg(type: String) = """
            <h1>This Server Doesn't Support $type Requests</h1>
            <h2>Use a GET request with boolean values for the following parameters<h2>
            <h3>'name'</h3>
            <h3>'bus'</h3>
            <h3>'sports'</h3>
            <h3>'sci'</h3>
            <h3>'world'</h3>
        """.trimIndent()
    }
}