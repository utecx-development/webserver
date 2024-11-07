package dev.ufo.server.cache;

import java.util.function.BiConsumer;

public interface Cache<V> {

    V get(String key);
    void put(String key, V value);
    void remove(String key);
    void forEach(BiConsumer<String, V> action);
    boolean containsKey(String key);

}
