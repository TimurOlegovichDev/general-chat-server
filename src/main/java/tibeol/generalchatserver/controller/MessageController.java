package tibeol.generalchatserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tibeol.generalchatserver.entity.Message;
import tibeol.generalchatserver.repository.MessageRepository;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageController {

    private final MessageRepository messageRepository;

    public List<Message> getMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }
}
