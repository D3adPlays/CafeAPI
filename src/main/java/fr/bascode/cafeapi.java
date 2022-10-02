package fr.bascode;

import com.sun.net.httpserver.HttpServer;
import fr.bascode.handlers.RootHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class cafeapi {
    public static void main(String[] args) throws IOException, InterruptedException {
        demarrageApi();
        demarrageWebsocket();
    }

    private static void demarrageWebsocket() throws IOException, InterruptedException {
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket listener = new ServerSocket(9090);
        try{
            while(true){
                String sin = sysin.readLine();
                System.out.println("Vous avez entré: " + sin);
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Client response: " + in.readLine());

                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    System.out.println("Sending Message...");
                    out.write("Hello\n from Java!\n");
                    out.flush();
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    public static void demarrageApi() throws IOException {
        int port = 6969;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("L'api web as bien été ouvert sur: http://localhost:" + port);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();
    }
}
