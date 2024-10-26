package tibeol.generalchatserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.dto.ClientDto;

@Component
public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации в JSON", e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json.trim(), clazz);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
