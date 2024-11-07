package dev.ufo.server.cache.hashmapcache;

import dev.ufo.server.cache.Cache;

import java.util.*;
import java.util.function.BiConsumer;

public class HashMapCache<V> implements Cache<V> {

    private final Map<String, V> cache;

    public HashMapCache() {
        cache = new HashMap<>();
    }

    @Override
    public V get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, V value) {
        cache.put(key, value);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public void forEach(BiConsumer<String, V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<String, V> entry : cache.entrySet()) {
            String k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            action.accept(k, v);
        }
    }

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

}
