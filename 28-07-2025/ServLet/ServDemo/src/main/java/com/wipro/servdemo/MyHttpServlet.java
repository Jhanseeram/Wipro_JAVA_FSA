package com.wipro.servdemo;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/httpdemo")
public class MyHttpServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet() method is called!");  // This prints to server console

        // Optional: send a response to the browser
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello from MyHttpServlet!</h1>");
    }
}
