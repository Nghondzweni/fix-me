package utilities.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Callable;

public class MessageReader implements Callable {

    private  Socket client;
    public MessageReader (Socket client){
        this.client = client;
        try{
            this.client.setSoTimeout(3000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    @Override
    public Object call() throws Exception {
        String readText = "";
        BufferedReader input;

        try {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            readText = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Text read from Message reader : " + readText);
        return null;
    }
}
