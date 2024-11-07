package dev.ufo.server.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CacheItem {

    private byte[] bytes;
    private String path;
    private String htmlType;

    public CacheItem(byte[] bytes, String path) {
        this.bytes = bytes;
        this.path = path;

    }

}
