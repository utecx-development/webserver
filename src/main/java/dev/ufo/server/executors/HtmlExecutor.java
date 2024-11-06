package dev.ufo.server.executors;

import com.sun.net.httpserver.HttpExchange;
import dev.ufo.Main;
import dev.ufo.server.object.Request;
import dev.ufo.usr.pathimp.Path;

import java.io.File;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class HtmlExecutor implements Executor{

    private final static String executedPath;
    static {
        try {
            executedPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + File.separator + "www";
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finalExecute(Request req, Path path, HttpExchange exchange) {

        try {
            System.out.println(req.getPath());
            String s = executedPath + req.getPath().replace("/", File.separator);
            if (req.getFileName() != null) {
                s = s + req.getFileName();
            } else {
                s = s + "index.html";
            }
            File file = new File(s);
            String mimeType = Files.probeContentType(file.toPath());

            if (mimeType == null) {
                mimeType = "text/html";
            }
            exchange.getResponseHeaders().set("Content-Type", mimeType);

            // Sende die gesammelten Daten an den Client
            byte[] result = Files.readAllBytes(file.toPath()); //outputStream.toByteArray();

            exchange.sendResponseHeaders(200, result.length);
            OutputStream os = exchange.getResponseBody();
            os.write(result);
            os.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
