package tibeol.generalchatserver.entity;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client userId;
}
