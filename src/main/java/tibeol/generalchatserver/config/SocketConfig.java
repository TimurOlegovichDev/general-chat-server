package tibeol.generalchatserver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.DatagramSocket;
import java.net.SocketException;

@Getter
@Component
public class SocketConfig {

    private final DatagramSocket socket;

    public SocketConfig(@Value("${port}") int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        System.out.println(socket.getLocalPort());
    }
}
