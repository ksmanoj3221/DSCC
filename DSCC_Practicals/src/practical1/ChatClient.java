package practical1;

import java.io.*;
import java.net.*;

public class ChatClient {
    private String hostname;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader keyboard;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket(hostname, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the chat server");

            // Thread for receiving messages from the server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Sending messages to the server
            String message;
            while ((message = keyboard.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (keyboard != null) keyboard.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected from the chat server");
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 12345);
        client.start();
    }
}

