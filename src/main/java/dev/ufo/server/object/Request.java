package dev.ufo.server.object;

import com.sun.net.httpserver.HttpExchange;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Request {
    private String path;
    private String method;
    private Map<String, String> parameters;
    private String fileName;
    private String fullPath;
    private HttpExchange exchange;
    //private IpAddress address;

    public Request(String path, String method, /*IpAddress address,*/ String[] parameters, String fileName, HttpExchange exchange) {
        this.path = path;
        this.method = method;
        //this.address = address;
        this.parameters = new HashMap<>();
        this.fileName = fileName;
        this.fullPath = exchange.getRequestURI().getPath();
        if (exchange.getRequestURI().getQuery() != null) {
            fullPath += exchange.getRequestURI().getQuery();
        }

        this.exchange = exchange;

        if (parameters == null) {
            if (this.fileName == null && !this.path.endsWith("/")) {
                this.path = path + "/";
            }
            return;
        }

        for (String s : parameters) {
            String[] keyValue = s.split("=");
            if (keyValue.length != 2) {
                System.out.println("Invalide request parameter found: '" + s + "'");
                continue;
            }
            this.parameters.put(keyValue[0], keyValue[1]);
        }
    }
}

