package practical3;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a socket to listen on port 9876
            socket = new DatagramSocket(9876);
            System.out.println("Date Time Server is running...");

            while (true) {
                // Buffer to hold incoming data
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // Receive request from client
                socket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Check if the request is for the date and time
                if (request.equalsIgnoreCase("GET_DATE_TIME")) {
                    String response = getCurrentDateTime();
                    
                    // Send the result back to the client
                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();
                    byte[] sendData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    socket.send(sendPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
