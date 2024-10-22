package tibeol.generalchatserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.util.List;

@Entity
@Setter
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;
    private char[] password;

    private InetAddress address;
    private int port;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;
}
