package tibeol.generalchatserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tibeol.generalchatserver.dao.ClientDao;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpResponse;
import tibeol.generalchatserver.service.ActionService;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RegistrationService implements ActionService {

    private final ClientDao clientDao;

    @Override
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
