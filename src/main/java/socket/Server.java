package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            Socket clientSocket = serverSocket.accept(); //ждем подключения
            InputStream from = clientSocket.getInputStream();
            OutputStream to = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(from));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(to), true);
            System.out.println("New connection accepted. Port: " + clientSocket.getPort());
            writer.println(String.format("What is your name?"));
            String name = reader.readLine();
            writer.println(String.format("How old are you?", name));
            String message = reader.readLine();
            int age = Integer.parseInt(message);
            if (age < 18) {
                writer.println(String.format("Good bay!"));
            } else {
                writer.println(String.format("Welcome! Are you child?, %s? (yes/no)", name));
            }
            message = reader.readLine();
            if (message.equals("yes")) {
                writer.println(String.format("What's his name?"));
            } else if (message.equals("no")) {
                writer.println(String.format("Why?, %s", name));
            } else {
                writer.println(String.format("I didn't understand the answer!, %s", name));
            }
            message = reader.readLine();
            writer.println(String.format("Good bay!"));
            System.out.println("Connection severed");
        } catch (IOException e) {
            System.out.println("Connection error");
        } catch (NumberFormatException e) {
            System.out.println("Not nmber format");
        }
    }
}
