package dev.ufo.server.filter.filters;

import dev.ufo.server.filter.Filter;
import dev.ufo.server.object.Request;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class SpamFilter implements Filter {

    private static final Map<InetAddress, Integer> addressMap = new HashMap<>();

    @Override
    public boolean doFilter(Request req) {

        InetAddress address = req.getExchange().getRemoteAddress().getAddress();

        if (!addressMap.containsKey(address)){
            addressMap.put(address, 1);
            return true;
        }

        int i = addressMap.get(address);
        i++;

        addressMap.put(address, i);

        System.out.println(i);

        return i <= 100;
    }
}
