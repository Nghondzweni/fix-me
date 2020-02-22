import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Router {
    private static ServerSocket broker;
    private static ServerSocket market;

    public static RouterServerIO brokerReader ;
    public static RouterServerIO marketReader ;

    public static ExecutorService executorService = Executors.newCachedThreadPool();



    public static void listenerSocket () throws IOException {
        broker = new ServerSocket(5000);
        market = new ServerSocket(5001);

        System.out.println("Broker Listening on port : 5000\nMarket Listening on port : 5001\n");

        brokerReader = new RouterServerIO(broker);
        marketReader = new RouterServerIO(market);

    }
    public static void main(String[] args) throws Exception {
        listenerSocket();
        int i =0;
        while (i < 5){
            Object future = brokerReader.call();
            System.out.println(future);
            i++;
        }

    }
}
