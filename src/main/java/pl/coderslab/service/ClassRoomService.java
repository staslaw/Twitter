package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.ClassRoom;
import pl.coderslab.repository.ClassRoomRepository;
import java.util.List;

@Service
public class ClassRoomService {
    @Autowired
    ClassRoomRepository classRoomRepository;

    public String[] save(ClassRoom classRoom) {
        String[] message = new String[2];
        try {
            classRoomRepository.save(classRoom);
            message[0] = "message";
            message[1] = "Sala " + classRoom.getNumber() + " dodana prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Sala nie została dodana.";
        }
        return message;
    }

    public String[] update(ClassRoom classRoom) {
        String[] message = new String[2];
        Long classRoomNumber = classRoom.getNumber();
        try {
            classRoomRepository.save(classRoom);
            message[0] = "message";
            message[1] = "Sala " + classRoomNumber + " zmodyfikowana prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Sala " + classRoomNumber + "nie została zmodyfikowana.";
        }
        return message;
    }

    public String[] remove(Long id) {
        String[] message = new String[2];
        Long classRoomNumber = find(id).getNumber();
        try {
            classRoomRepository.delete(id);
            message[0] = "message";
            message[1] = "Sala " + classRoomNumber + " została usunięta.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Nie możesz usunąć sali " + classRoomNumber + ", jest ona powiązana z innymi elementami systemu.";
        }
        return message;
    }

    public ClassRoom find(Long id) {
        return classRoomRepository.findOne(id);
    }

    public List<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }
}