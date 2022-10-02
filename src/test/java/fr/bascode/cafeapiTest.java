package fr.bascode;


//codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server


import com.sun.net.httpserver.HttpServer;
import fr.bascode.handlers.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

class cafeapiTest {
    public static void main(String[] args) throws IOException {
        int port = 6969;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Le serveur as bien été ouvert sur: http://localhost:" + port);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();
    }
}