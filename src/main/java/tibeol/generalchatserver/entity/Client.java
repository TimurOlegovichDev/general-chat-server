package tibeol.generalchatserver.entity;

import jakarta.persistence.*;
import lombok.*;
import tibeol.generalchatserver.dto.ClientDto;
import tibeol.generalchatserver.net.UdpRequest;

import java.net.InetAddress;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Client {

    @Id
    private final String userName;

    private final char[] password;

    private final InetAddress address;
    private final int port;

    @OneToMany(mappedBy = "user_name")
    private List<Message> messages;

    public Client(ClientDto clientDto, UdpRequest request) {
        this.userName = clientDto.userName();
        this.password = clientDto.password();
        this.address = request.getAddress();
        this.port = request.getPort();
    }
}
