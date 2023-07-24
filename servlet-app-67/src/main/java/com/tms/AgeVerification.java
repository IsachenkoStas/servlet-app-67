package com.tms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/age")
public class AgeVerification extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String age = req.getParameter("check");
        if (age == null) {
            writer.println("It is Age Verification");
        } else if (Integer.parseInt(age) >= 18) {
            writer.println("Adult");
        } else if (Integer.parseInt(age) > 0 && Integer.parseInt(age) < 18) {
            writer.println("Underage");
        } else {
            writer.println("ERROR^-^");
        }
        writer.close();
    }
}
