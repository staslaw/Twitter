package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Team> findAllTeamsWithSubject(Long id) {
        return lessonRepository.findAllTeamsWithSubjectQuery(id);
    }
}
