package utilities.IO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class MessageWriter  implements Callable {
    private Socket client;
    private String message;

    public MessageWriter(Socket client, String message){
        this.client = client;
        this.message = message;
    }

    public Socket getClient() {
        return client;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Object call() throws Exception {
        PrintWriter output = null;

        try {
            output = new PrintWriter(client.getOutputStream(), true);
            output.println(message);
        } catch (ConnectException e){
            System.out.println("Error : Failed to connect to client");
        } catch (IOException e){
            System.out.println("Error : Failed to Write to  client");
        }
        return null;
    }
}
