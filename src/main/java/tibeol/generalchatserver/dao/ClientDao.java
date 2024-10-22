package tibeol.generalchatserver.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClientDao {

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client findById(Long id) {
       if(clientRepository.findById(id).isEmpty()){
           throw new NoSuchElementException("Client with id " + id + " not found");
       } else {
           return clientRepository.findById(id).get();
       }
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
