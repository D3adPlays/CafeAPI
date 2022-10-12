package fr.bascode.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.bascode.cafeapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

public class Esp8266 implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String,String> parms = cafeapi.queryToMap(exchange.getRequestURI().getQuery());
        String response = null;
        if(parms.containsKey("status")){
            response = cafeapi.statusMachine + "";
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else {
            response = "Erreur : Paramètre manquant status";
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }

        
        
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
