package dev.ufo.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dev.ufo.server.etc.SimpleExchange;
import dev.ufo.server.object.Request;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

public class Server {
    private final Framework framework;

    public Server(Framework framework) {
        this.framework = framework;
    }

    public void start(int port) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", this::handleRequest);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(HttpExchange exchange) throws IOException {

        Request request = getRequest(exchange);

        String response;
        try {
            response = framework.handleRequest(request);
            if (Objects.equals(response, "--")) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        SimpleExchange.respond(response.getBytes(), exchange, null);

    }

    private static Request getRequest(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();
        String query = exchange.getRequestURI().getQuery();
        String[] parameters = null;

        if (query != null) {
            parameters = query.split("&");
        }

        String[] splitPath = path.split("/");

        String fileName = null;

        if (splitPath.length > 0) {
            String s = splitPath[splitPath.length - 1];
            if (s != null && s.contains(".")) {
                fileName = splitPath[splitPath.length - 1];

                path = path.replace(fileName, "");
            }
        }

        return new Request(
                path,
                exchange.getRequestMethod(),
                //new IpAddress(exchange.getRemoteAddress()),
                parameters,
                fileName,
                exchange
        );
    }
}
