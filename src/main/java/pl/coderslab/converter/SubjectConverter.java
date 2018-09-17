package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Subject;
import pl.coderslab.repository.SubjectRepository;

public class SubjectConverter implements Converter<String, Subject> {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject convert(String source) {
        return subjectRepository.findOne(Long.valueOf(source));
    }
}
