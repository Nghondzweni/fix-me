import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

public class RouterServerIO implements Callable {
    private ServerSocket serverSocket;      //Socket Connection for the Server Side
    private Socket client;                  //Socket Connection for client side




    public RouterServerIO(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public RouterServerIO(Socket client){
        this.client = client;
    }

    public Socket getClient() {
        return client;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort(){
        return this.serverSocket.getLocalPort();
    }


    @Override
    public Object call() throws Exception {
        String input = "";
        BufferedReader incoming = null;
        PrintWriter outgoing = null;


        client = serverSocket.accept();
        System.out.println("New Connection from : " + client);
        outgoing = new PrintWriter(client.getOutputStream());
        incoming = new BufferedReader(new InputStreamReader(client.getInputStream()));
        input = incoming.readLine();

        System.out.println("Message from broker is : " + input);
        outgoing.println("Message from router : You said your name is "  + input);


        return input;
    }
}
