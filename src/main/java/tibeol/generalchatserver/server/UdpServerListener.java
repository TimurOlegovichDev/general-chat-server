package tibeol.generalchatserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Slf4j
@Component
public class UdpServerListener extends Thread{

    private final int port;

    private final String address;

    private DatagramSocket socket;
    private volatile boolean running = true;

    public UdpServerListener(@Value("${port}") int port,
                     @Value("${address}") String address) {
        this.port = port;
        this.address = address;
    }

    @Override
    public void run() {
        byte[] buf = new byte[1024];
        try {
            log.info("UdpServer started on address: {} and port: {}", address, port);
            this.socket = new DatagramSocket(port);
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                receivePacket(packet);
            }
        } catch (IOException e) {
            log.error("Error while receiving packet: {}", e.getMessage());
        } finally {
            closeSocket();
        }
    }

    public void receivePacket(DatagramPacket packet) {
        String data = new String(packet.getData(), 0, packet.getLength());
        log.info("Received packet from {}: {}",
                packet.getAddress().getHostAddress(),
                data);
    }

    private void closeSocket() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            log.info("UdpServer socket closed");
        }
    }
}