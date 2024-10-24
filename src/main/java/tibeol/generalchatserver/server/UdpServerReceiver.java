package tibeol.generalchatserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.config.SocketConfig;
import tibeol.generalchatserver.handler.UdpPacketHandler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

@Slf4j
@Component
public class UdpServerReceiver extends Thread {

    private final UdpPacketHandler packetHandler;


    private final DatagramSocket socket;
    private volatile boolean running = true;

    public UdpServerReceiver(@Autowired UdpPacketHandler packetHandler,
                             @Autowired SocketConfig socketConfig) {
        super();
        this.packetHandler = packetHandler;
        this.socket = socketConfig.getSocket();
        this.start();
    }

    @Override
    public void run() {
        byte[] buf = new byte[1024];
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                packetHandler.handle(packet);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}