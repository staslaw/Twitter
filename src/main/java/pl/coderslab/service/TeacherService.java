package pl.coderslab.service;

import pl.coderslab.entity.Teacher;

public interface TeacherService {
    Teacher findByTeacherName(String name);

    void saveTeacher(Teacher teacher);
}
