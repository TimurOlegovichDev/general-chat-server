package tibeol.generalchatserver.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tibeol.generalchatserver.controller.ClientController;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.enums.UdpMethod;
import tibeol.generalchatserver.net.UdpRequest;
import tibeol.generalchatserver.net.UdpResponse;
import tibeol.generalchatserver.server.UdpServerSender;
import tibeol.generalchatserver.service.ClientService;

import java.io.IOException;
import java.net.DatagramPacket;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UdpPacketHandler {

    private final ClientController clientController;
    private final ClientService clientService;
    private final UdpServerSender serverSender;

    public void handle(DatagramPacket packet) throws IOException {
        UdpRequest request = new UdpRequest(
                new String(packet.getData()).replaceAll("\\s+", ""),
                packet.getAddress(),
                packet.getPort()
        );
        log.info("Получен пакет: {}", request);
        switch (request.getMethod()) {
            case REGISTER -> {
                clientController.register(
                        new Client(
                                clientService.deserializeClientDto(request.getData()),
                                request
                        )
                );
            }
            case LOGIN -> {
                clientController.login(
                        new Client(
                                clientService.deserializeClientDto(request.getData()),
                                request
                        )
                );
            }
            default -> handleNotSupportedPacket(packet);
        }
    }

    private void handleNotSupportedPacket(DatagramPacket packet) {
        // Обработка неподдерживаемого пакета
    }

    public void handleIOException(IOException e, Client client) {
        log.error("Ошибка: {}", e.getMessage());
        serverSender.send(
                UdpResponse.serverError("Ошибка на сервере: " + e.getMessage()),
                client
        );
    }
}