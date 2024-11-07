package dev.ufo;

import dev.ufo.server.Framework;
import dev.ufo.server.Server;
import dev.ufo.server.filter.filters.SpamFilter;
import dev.ufo.test.HtmlTest;
import dev.ufo.test.UsrController;

public class Main {
    public static void main(String[] args) {

        Framework framework = new Framework();

        framework.registerController(UsrController.class, true);
        framework.registerController(HtmlTest.class, true);
        framework.addFilter(new SpamFilter());

        new Server(framework).start(8080);

    }
}