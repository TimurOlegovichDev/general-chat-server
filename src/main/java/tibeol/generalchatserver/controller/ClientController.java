package tibeol.generalchatserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.dao.ClientDao;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.server.UdpServerSender;
import tibeol.generalchatserver.service.impl.LoginService;
import tibeol.generalchatserver.service.impl.RegistrationService;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClientController {

    private final ClientDao clientDao;
    private final RegistrationService registrationService;
    private final UdpServerSender serverSender;
    private final LoginService loginService;

    public void login(Client client) {
        serverSender.send(
                loginService.serve(client),
                client
        );
    }

    public void register(Client client) {
        serverSender.send(
                registrationService.serve(client),
                client
        );
    }

    public void addClient(Client client) {
        clientDao.save(client);
    }

    public void getClient(String name) {
        clientDao.findById(name);
    }
}
