package tibeol.generalchatserver.net;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tibeol.generalchatserver.enums.UdpMethod;

import java.net.InetAddress;

@Getter
public class UdpRequest {
    private final UdpMethod method;
    private final String data;
    private final InetAddress address;
    private final int port;

    public UdpRequest(String input, InetAddress address, int port) {
        String[] parts = input.split("%", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Неверный формат строки: " + input + " Ожидается: METHOD:ID:DATA");
        }
        this.method = UdpMethod.of(parts[0]);
        this.data = parts[1].trim();
        this.address = address;
        this.port = port;
    }

    @Override
    public String toString() {
        return "UdpRequest{" +
                "method=" + method +
                ", data='" + data + '\'' +
                ", address=" + address +
                ", port=" + port +
                '}';
    }
}