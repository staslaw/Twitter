package pl.coderslab.service;

import pl.coderslab.entity.Student;

public interface StudentService {
    Student findByStudentName(String name);

    void saveStudent(Student student);
}