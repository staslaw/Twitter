package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    public String[] sendMessage(String text, Long userToId, Long userFrom) {
        String[] answer = new String[2];
        try {
            Message message = new Message();
            message.setFromUser(userRepository.findOne(userFrom));
            message.setToUser(userRepository.findOne(userToId));
            message.setText(text);
            message.setReaded(0);
            Date date = Date.valueOf(LocalDate.now());
            Time time = Time.valueOf(LocalTime.now());
            message.setCreatedDate(date);
            message.setCreatedTime(time);
            messageRepository.save(message);
            answer[0] = "message";
            answer[1] = "message was sent";
        } catch (Exception e) {
            answer[0] = "messageDanger";
            answer[1] = "message was not sent";
        }
        return answer;
    }

    public List<Message> messagesSent(Long userFromId) {
        return messageRepository.findAllByFromUser(userRepository.findOne(userFromId));
    }

    public List<Message> messagesGot(Long userToId) {
        return messageRepository.findAllByToUser(userRepository.findOne(userToId));
    }
}
