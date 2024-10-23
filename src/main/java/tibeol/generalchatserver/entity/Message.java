package tibeol.generalchatserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private final String text;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private final Client user_name;

    public Message() {
        this.text = "Empty";
        this.user_name = null;
    }
}
