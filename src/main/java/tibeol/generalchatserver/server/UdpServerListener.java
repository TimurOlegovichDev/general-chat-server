package tibeol.generalchatserver.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.handler.UdpPacketHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Slf4j
@Component
public class UdpServerListener extends Thread{

    private final int port;
    private final String address;

    private final UdpPacketHandler packetHandler;

    private volatile boolean running = true;

    public UdpServerListener(@Value("${port}") int port,
                             @Value("${address}") String address,
                             @Autowired UdpPacketHandler packetHandler) {
        this.port = port;
        this.address = address;
        this.packetHandler = packetHandler;
        this.start();
    }

    @Override
    public void run() {
        byte[] buf = new byte[1024];
        try(DatagramSocket socket = new DatagramSocket(port)) {
            log.info("UdpServer started on address: {} and port: {}", address, port);
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                packetHandler.handle(packet);
            }
        } catch (IOException e) {
            log.error("Error while receiving packet: {}", e.getMessage());
        }
    }
}