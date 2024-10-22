package tibeol.generalchatserver.handler;

import tibeol.generalchatserver.enums.UdpMethod;

import java.net.DatagramPacket;

public class UdpPacketHandler {

    public void handle(DatagramPacket packet){
        String data = new String(packet.getData());
        switch(UdpMethod.of(data)){
            case GET -> {

            }
            case POST -> {}
            default -> handleNotSupportedPacket(packet);
        }
    }

    private void handleNotSupportedPacket(DatagramPacket packet){

    }
}
