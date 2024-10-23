package tibeol.generalchatserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tibeol.generalchatserver.dto.ClientDto;
import tibeol.generalchatserver.entity.Client;

import java.io.IOException;

@Service
public class ClientService {

    private final ObjectMapper objectMapper;

    public ClientService() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Десериализует JSON строку в объект Client.
     *
     * @param json строка в формате JSON
     * @return объект Client
     * @throws IOException если произошла ошибка при десериализации
     */
    public ClientDto deserializeClientDto(String json) throws IOException {
        return objectMapper.readValue(json, ClientDto.class);
    }
}