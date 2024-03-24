package com.aui;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GPIHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/HTML; charset=UTF-8");
        response.getWriter().print(
                """
                        <html>
                        <body>
                        <h1>Bismillah</h1>
                        <p>This is my very first, embedded Tomcat, HTML Page!</p>
                        </body>
                        </html>
                        """
        );
    }
}
