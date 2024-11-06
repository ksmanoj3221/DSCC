package practical2;

import java.net.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a socket to send data
            socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Read user input for calculation
                System.out.print("Enter calculation (e.g., '5 + 3') or 'exit' to quit: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break; // Exit the loop
                }

                // Send the request to the server
                byte[] sendData = input.getBytes();
                InetAddress serverAddress = InetAddress.getByName("localhost");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
                socket.send(sendPacket);

                // Receive the response from the server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Print the response from the server
                System.out.println("Server response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

