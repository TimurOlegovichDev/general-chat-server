package tibeol.generalchatserver.service.impl;

import org.springframework.stereotype.Service;
import tibeol.generalchatserver.dto.MessageDto;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.entity.Message;
import tibeol.generalchatserver.enums.UdpResponseCode;
import tibeol.generalchatserver.net.UdpResponse;
import tibeol.generalchatserver.service.ActionService;
import tibeol.generalchatserver.util.JsonConverter;

@Service
public class MessageService implements ActionService<MessageDto> {
    private final JsonConverter jsonConverter;

    public MessageService(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public UdpResponse serve(MessageDto messageDto) {
        return new UdpResponse(
                UdpResponseCode.HAS_MESSAGE,
                jsonConverter.toJson(messageDto)
        );
    }
}
