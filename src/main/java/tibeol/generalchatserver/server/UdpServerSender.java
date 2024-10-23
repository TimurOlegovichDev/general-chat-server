package tibeol.generalchatserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpResponse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class UdpServerSender {

    public void send(UdpResponse response, Client client){
        try(DatagramSocket socket = new DatagramSocket(
                client.getPort(),
                client.getAddress())){
            socket.send(new DatagramPacket(
                    response.getBody().getBytes(StandardCharsets.UTF_8),
                    response.getBody().getBytes(StandardCharsets.UTF_8).length
            ));
            log.info("Sent UDP response");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
