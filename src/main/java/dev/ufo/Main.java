package dev.ufo;

import dev.ufo.server.Framework;
import dev.ufo.server.Server;
import dev.ufo.test.HtmlTest;
import dev.ufo.test.UsrController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Framework framework = new Framework();
        framework.registerController(UsrController.class);
        framework.registerController(HtmlTest.class);
        new Server(framework).start(8080);
    }
}