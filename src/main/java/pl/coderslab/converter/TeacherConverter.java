package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Teacher;
import pl.coderslab.repository.TeacherRepository;

public class TeacherConverter implements Converter<String, Teacher> {
    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public Teacher convert(String source) {
        return teacherRepository.findOne(Long.valueOf(source));
    }
}
