package tibeol.generalchatserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.config.SocketConfig;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpResponse;
import tibeol.generalchatserver.util.JsonConverter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class UdpServerSender {

    private final DatagramSocket socket;
    private final JsonConverter jsonConverter;

    public UdpServerSender(@Autowired SocketConfig socketConfig, @Autowired JsonConverter jsonConverter) {
        this.socket = socketConfig.getSocket();
        this.jsonConverter = jsonConverter;
    }

    public void send(UdpResponse response, InetAddress address, int port) {
        try {
            byte[] data = jsonConverter.toJson(response).getBytes();
            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    address,
                    port
            );
            socket.send(packet);
            log.info("Sent UDP response to client with address: {} and port: {} ",
                    address,
                    port
            );
        } catch (IOException e) {
            log.error("Error sending UDP response: {}", e.getMessage());
        }
    }
}
