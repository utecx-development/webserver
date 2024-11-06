package dev.ufo.test;

import java.util.HashMap;
import java.util.Map;

public class Usr {

    private final Map<Integer, String> worker = new HashMap<>();

    public Usr() {
        worker.put(1, "Tim");
    }

    public String getWorker(int id) {
        if (!worker.containsKey(id)) {
            return "not found";
        }
        return worker.get(id);
    }

}
