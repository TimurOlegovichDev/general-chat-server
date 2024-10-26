package tibeol.generalchatserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tibeol.generalchatserver.dto.MessageDto;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
public class Message {

    public Message(MessageDto messageDto, Client userName) {
        this.text = messageDto.getText();
        this.user_name = userName;
    }

    @Id
    private UUID Uuid = UUID.randomUUID();

    @Getter
    private final String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    @Getter
    private final Client user_name;

    public Message() {
        this.text = "Empty";
        this.user_name = null;
    }
}
