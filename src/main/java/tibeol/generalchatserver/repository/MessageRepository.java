package tibeol.generalchatserver.repository;

import org.springframework.data.repository.CrudRepository;
import tibeol.generalchatserver.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
