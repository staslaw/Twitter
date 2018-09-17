package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Student;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository,
                              RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Student findByStudentName(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public void saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setEnabled(1);
        student.setRole(roleRepository.findOneRoleByNameQuery("ROLE_STUDENT"));
        studentRepository.save(student);
    }
}