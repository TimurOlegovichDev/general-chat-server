package tibeol.generalchatserver.repository;

import org.springframework.data.repository.CrudRepository;
import tibeol.generalchatserver.entity.Client;

public interface ClientRepository extends CrudRepository<Client, String> {

}
