package tibeol.generalchatserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tibeol.generalchatserver.dao.ClientDao;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpResponse;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RegistrationService {

    private final ClientDao clientDao;

    public UdpResponse serve(Client client){
        try {
            clientDao.findById(client.getUserName());
            return UdpResponse.bad("Пользователь с таким именем уже есть в системе.");
        } catch (Exception e) {
            clientDao.save(client);
            return UdpResponse.ok("Регистрация прошла успешно.");
        }
    }
}
