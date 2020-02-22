import utilities.IO.MessageReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Broker {
    private static Socket socket;
    public static ExecutorService executorService = Executors.newCachedThreadPool();


    private static Socket connect() {
        socket = null;
        try{
            socket = new Socket("localhost" , 5000);
        }
        catch (IOException e){

        }
        return socket;
    }

    public static void main(String[] args) throws IOException {
        socket = connect();
        System.out.println("Connection Established");
        String input;
        String output;
        Scanner scanner = new Scanner(System.in);
        System.out.println(socket.getInputStream());
        BufferedReader incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outgoing = new PrintWriter(socket.getOutputStream(), true);

        int i = 0;

        while(i < 5) {
            output = scanner.nextLine();
            System.out.println("Input Entered on Broker console : " + output);
            outgoing.println(output);

            input = incoming.readLine();

            System.out.println(i + ": Router Says : '" + input + " ' printed from Broker");

            i++;

        }
    }
}
