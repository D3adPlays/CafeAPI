package fr.bascode.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Bienvenue sur le serveur de l'API de café!\n"+
                "Ce serveur doit compoerter les routes suivantes:\n\n" +
                "/getstatus (Retourne le statut de la machine stocké dans une variable)\n\n" +
                "Envoie 0 si la macine ne fait rien\n" +
                "Envoie 1 si la macine fait du café\n" +
                "Envoie 2 si la macine maintiens au chaud le café\n\n" +

                "/setstatus (Permet de changer le statut de la machine)\n\n" +
                "Recevoire 0 pour eteindre la macine\n" +
                "Recevoire 1 pour faire du café\n" +
                "Recevoire 2 pour maintenir au chaud le café\n\n" +
                "Je vous mets aussi le code source de l'api sur github:\n" +
                "Et vous laisse un lien vers un tuto pour faire une api web en java:\n" +
                "https://codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
