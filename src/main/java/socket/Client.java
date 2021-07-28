package socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String LOCALHOST = "127.0.0.1";
    public static final String HOST = "netology.homework";
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        changeHosts();
        try {
            Socket socket = new Socket(HOST, Server.SERVER_PORT);
            InputStream from = socket.getInputStream();
            OutputStream to = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(from));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(to));
            System.out.println(reader.readLine());
            String messageClient;
            while (true) {
                messageClient = scanner.nextLine();
                writer.write(messageClient);
                writer.newLine();
                writer.flush();
                String messeageServer = reader.readLine();
                System.out.println(messeageServer);
                if (messeageServer.equals("Good bay!")) break;
            }
        } catch (IOException e) {
            System.out.println("Connection error");
        }
    }

    public static void changeHosts() {
        try (FileWriter writer = new FileWriter("hosts", true)) {
            writer.write(" " + LOCALHOST + " " + HOST);
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
