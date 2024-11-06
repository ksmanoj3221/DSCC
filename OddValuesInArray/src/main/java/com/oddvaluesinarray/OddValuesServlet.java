package com.oddvaluesinarray;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OddValuesServlet", urlPatterns = {"/sum"})
public class OddValuesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] values = request.getParameterValues("numbers");
        List<Integer> oddValues = new ArrayList<>();

        // Check if there are values provided in the request
        if (values != null && values.length > 0) {
            for (String value : values) {
                // Split the values by comma and process each one
                String[] nums = value.split(",");
                for (String num : nums) {
                    try {
                        int number = Integer.parseInt(num.trim());
                        if (number % 2 != 0) { // Check if the number is odd
                            oddValues.add(number);
                        }
                    } catch (NumberFormatException e) {
                        // Handle the case where the input is not a valid integer
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + num);
                        return;
                    }
                }
            }
        }

        // Prepare the odd values as a comma-separated string
        String oddValuesString = oddValues.toString().replaceAll("[\\[\\]]", "");

        // Redirect back to the form with both the numbers and oddValues as query parameters
        response.sendRedirect("/index.html?numbers=" + request.getParameter("numbers") + "&oddValues=" + oddValuesString);
    }
}
