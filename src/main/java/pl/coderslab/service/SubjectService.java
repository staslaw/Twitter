package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Subject;
import pl.coderslab.repository.SubjectRepository;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public String[] save(Subject subject) {
        String[] message = new String[2];
        try {
            subjectRepository.save(subject);
            message[0] = "message";
            message[1] = "Przedmiot " + subject.getName() + " dodany prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Przedmiot nie został dodany.";
        }
        return message;
    }

    public String[] remove(Long id) {
        String[] message = new String[2];
        String subjectName = subjectRepository.findOne(id).getName();
        try {
            subjectRepository.delete(id);
            message[0] = "message";
            message[1] = "Przedmiot " + subjectName + " został usunięty.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Nie możesz usunąć przedmiotu " + subjectName + ", jest on powiązany z innymi elementami systemu.";
        }
        return message;
    }

    public Subject find(Long id) {
        return subjectRepository.findOne(id);
    }

    public String[] update(Subject subject) {
        String[] message = new String[2];
        String subjectName = subject.getName();
        try {
            subjectRepository.save(subject);
            message[0] = "message";
            message[1] = "Przedmiot " + subjectName + " zmodyfikowany prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Przedmiot " + subjectName + " nie został zmodyfikowany.";
        }
        return message;
    }
}