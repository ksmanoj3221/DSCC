package com.sumarray;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "SumArrayServlet", urlPatterns = {"/sum"})
public class SumArrayServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] values = request.getParameterValues("numbers");
        int sum = 0;

        // Check if there are values provided in the request
        if (values != null && values.length > 0) {
            for (String value : values) {
                // Trim and check for empty values before parsing
                String[] nums = value.split(",");
                for (String num : nums) {
                    try {
                        sum += Integer.parseInt(num.trim());
                    } catch (NumberFormatException e) {
                        // Handle the case where the input is not a valid integer
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + num);
                        return;
                    }
                }
            }
        }

        // Redirect back to the root URL with the sum and numbers as query parameters
        String numbersParam = request.getParameter("numbers");
        response.sendRedirect("/?sum=" + sum + "&numbers=" + numbersParam);
    }
}
