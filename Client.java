import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.100.95", 8080);
        System.out.println("Connected to server: " + socket.getInetAddress().getHostName());

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String message = reader.readLine();
        System.out.println("Received message: " + message);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        message = "i am client";
        writer.write(message);
        writer.newLine();
        writer.flush();

        socket.close();
    }
}