package fr.bascode;

import com.sun.net.httpserver.HttpServer;
import fr.bascode.handlers.Esp8266;
import fr.bascode.handlers.RootHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class cafeapi {

    public static int statusMachine = 0;

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        demarrageApi();
        demarrageConsoleInput();
    }
    public static void demarrageConsoleInput() {
        new Thread(() -> {
            while (true) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String line = reader.readLine();
                    if (line.equals("exit")) {
                        System.exit(0);
                    }
                    if (line.equals("help")) {
                        System.out.println("Liste des commandes :");
                        System.out.println("exit : Quitter l'application");
                        System.out.println("help : Afficher la liste des commandes");
                        System.out.println("send : Envoyer un message à tous les clients");
                    }
                    if (line.startsWith("set")) {
                        statusMachine = Integer.parseInt(line.split(" ")[1]);
                        System.out.println("Le statut de la machine à café est maintenant : " + statusMachine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void demarrageApi() throws IOException {
        int port = 6969;
        System.out.println("L'api web as bien été ouvert sur: http://localhost:" + port);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/update-cafe", new Esp8266());
        server.setExecutor(null);
        server.start();
    }

    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }return result;
    }
}
