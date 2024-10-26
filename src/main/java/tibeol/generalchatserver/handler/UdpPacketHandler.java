package tibeol.generalchatserver.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.controller.ClientController;
import tibeol.generalchatserver.controller.MessageController;
import tibeol.generalchatserver.dao.ClientDao;
import tibeol.generalchatserver.dto.ClientDto;
import tibeol.generalchatserver.dto.MessageDto;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.entity.Message;
import tibeol.generalchatserver.net.UdpRequest;
import tibeol.generalchatserver.net.UdpResponse;
import tibeol.generalchatserver.server.UdpServerSender;
import tibeol.generalchatserver.service.impl.MessageService;
import tibeol.generalchatserver.util.JsonConverter;

import java.io.IOException;
import java.net.DatagramPacket;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UdpPacketHandler {

    private final ClientController clientController;
    private final JsonConverter jsonConverter;
    private final UdpServerSender serverSender;
    private final MessageController messageController;
    private final MessageService messageService;

    public void handle(DatagramPacket packet) {
        UdpRequest request = new UdpRequest(
                new String(packet.getData()),
                packet.getAddress(),
                packet.getPort()
        );
        log.info("Получен пакет: {}", request);
        switch (request.getMethod()) {
            case CONNECT -> {
                serverSender.send(
                        UdpResponse.ok("Успешное подключено"),
                        request.getAddress(),
                        request.getPort()
                );
            }
            case REGISTER -> {
                clientController.register(
                        new Client(
                                jsonConverter.fromJson(request.getData(), ClientDto.class),
                                request
                        )
                );
            }
            case LOGIN -> {
                clientController.login(
                        new Client(
                                jsonConverter.fromJson(request.getData(), ClientDto.class),
                                request
                        )
                );
            }
            case POST_MESSAGE -> {
                MessageDto dto = jsonConverter.fromJson(request.getData(), MessageDto.class);
                Message msg = messageController.addMessage(
                        new Message(
                            dto, clientController.getClient(dto.getSenderName())
                        )
                );
                    for(Client client : clientController.getClients()){
                        serverSender.send(
                                messageService.serve(
                                        new MessageDto(
                                                msg.getUser_name().getUserName(),
                                                msg.getText()
                                        )
                                ),
                                client.getAddress(),
                                client.getPort()
                        );
                }
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
                client.getAddress(),
                client.getPort()
        );
    }
}