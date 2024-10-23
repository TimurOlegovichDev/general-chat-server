package tibeol.generalchatserver.net;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tibeol.generalchatserver.enums.UdpMethod;

import java.net.InetAddress;

@Getter
public class UdpRequest {
    private final UdpMethod method;
    private final Long entityId;
    private final String data;
    private final InetAddress address;
    private final int port;

    public UdpRequest(String input, InetAddress address, int port) {
        String[] parts = input.split("&");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки: " + input + " Ожидается: METHOD:ID:DATA");
        }

        this.method = UdpMethod.of(parts[0]);
        this.entityId = Long.valueOf(parts[1]);
        this.data = parts[2];
        this.address = address;
        this.port = port;
    }
}