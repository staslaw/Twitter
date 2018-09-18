package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Student;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.StudentRepository;

import java.util.Random;


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

    public String createPassword() {
        Random random = new Random();
        String password = "";
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt(52) + 65;
            if (n > 90) {
                n = n + 6;
            }
            char x = (char)n;
            password += x;
        }
        return password;
    }
}