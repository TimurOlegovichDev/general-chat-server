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
public class LoginService implements ActionService {

    private final ClientDao clientDao;

    @Override
    public UdpResponse serve(Client client) {
        try {
            Client expectedClient = clientDao.findById(client.getUserName());
            if(expectedClient.getPassword().equals(client.getPassword())) {
                return UdpResponse.ok("Вход выполнен успешно");
            }
            return UdpResponse.bad("Неверный пароль");
        } catch (Exception e) {
            return UdpResponse.bad("Пользователя с таким именем нет в системе");
        }
    }
}
