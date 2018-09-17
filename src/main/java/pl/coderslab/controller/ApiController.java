package pl.coderslab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Teacher;
import pl.coderslab.repository.SubjectRepository;
import pl.coderslab.repository.TeacherRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public ApiController(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping("/subject/{selectedSubjectId}")
    public List<Teacher> getTeachers(@PathVariable Long selectedSubjectId){
//        return teacherRepository.findAll();
        return subjectRepository.findOne(selectedSubjectId).getTeachers();
    }
}
