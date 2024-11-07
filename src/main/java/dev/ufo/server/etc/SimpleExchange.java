package dev.ufo.server.etc;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.util.Objects;

public class SimpleExchange {

    public static void respond(byte[] response, HttpExchange exchange, String responseType) {

        try {
            exchange.getResponseHeaders().set("Content-Type", Objects.requireNonNullElse(responseType, "text/html"));

            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
