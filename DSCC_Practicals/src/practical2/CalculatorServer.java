package practical2;

import java.net.*;

public class CalculatorServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a socket to listen on port 9876
            socket = new DatagramSocket(9876);
            System.out.println("Calculator Server is running...");

            while (true) {
                // Buffer to hold incoming data
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // Receive request from client
                socket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Process the request and perform calculation
                String response = processRequest(request);
                
                // Send the result back to the client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private static String processRequest(String request) {
        String[] parts = request.split(" ");
        if (parts.length != 3) {
            return "Invalid request. Use: <number1> <operator> <number2>";
        }

        double num1 = Double.parseDouble(parts[0]);
        String operator = parts[1];
        double num2 = Double.parseDouble(parts[2]);
        double result;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return "Error: Division by zero.";
                }
                result = num1 / num2;
                break;
            default:
                return "Error: Unknown operator.";
        }
        return "Result: " + result;
    }
}
