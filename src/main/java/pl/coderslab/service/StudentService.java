package pl.coderslab.service;

import pl.coderslab.entity.Student;

public interface StudentService {
    Student findByStudentName(String name);

    String[] saveStudent(Student student);
}