package dev.ufo.server.object;

import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Getter
@Setter
public class IpAddress {

    private int[] bytes;
    private int port;

    public IpAddress(int... i) {
        if (i.length != 4 && i.length != 5) {
            throw new RuntimeException("The given int array does not contain 4 blocks");
        }
        this.bytes = new int[4];
        for (int j = 0; j != 4; j++) {
            int ij = i[j];
            if (!(ij >= 0 && ij <= 255)) {
                throw new RuntimeException("The number: '" + ij + "' is not in the 256 bit range of ipv4!");
            }
            this.bytes[j] = ij;
        }
        if (i.length == 5) this.port = i[4];
    }

    public int getBlock(int i) {
        return bytes[i];
    }

    public String get() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i != bytes.length; i++) {
            b.append(bytes[i]);
        }
        return b.toString();
    }
}
