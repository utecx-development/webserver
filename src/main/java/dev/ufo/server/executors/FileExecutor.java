package dev.ufo.server.executors;

import dev.ufo.Main;
import dev.ufo.server.etc.SimpleExchange;
import dev.ufo.server.object.Request;
import dev.ufo.server.object.ResponseMapping;
import dev.ufo.usr.pathimp.Path;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class FileExecutor implements Executor{

    private final static String executedPath;
    static {
        try {
            executedPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + File.separator + "www";
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseMapping fileResponse(Request req, Path path) {
        try {
            System.out.println(req.getPath());
            String s = executedPath + req.getPath().replace("/", File.separator);
            if (req.getFileName() != null) {
                s = s + req.getFileName();
            } else {
                s = s + "index.html";
            }
            File file = new File(s);

            if (!file.exists()) {
                return new ResponseMapping("<html>file not found</html>".getBytes(), "text/html");
            }

            String mimeType = Files.probeContentType(file.toPath());

            if (mimeType == null) {
                mimeType = "text/html";
            }

            return new ResponseMapping(Files.readAllBytes(file.toPath()), mimeType);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
