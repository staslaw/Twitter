package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Admin;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Teacher;
import pl.coderslab.repository.AdminRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.StudentRepository;
import pl.coderslab.repository.TeacherRepository;
import java.util.List;
import java.util.Random;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    protected MailService mailService;

    public StudentServiceImpl(StudentRepository studentRepository,
                              RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder,
                              AdminRepository adminRepository, TeacherRepository teacherRepository, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.mailService = mailService;
    }


    @Override
    public Student findByStudentName(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public String[] saveStudent(Student student) {
        String[] message = new String[3];
        student.setPassword(createPassword());
        student.setEnabled(1);
        student.setRole(roleRepository.findOneRoleByNameQuery("ROLE_STUDENT"));
        message[2] = mailService.generateAndSendEmail(student.getUsername(), student.getUsername(), student.getPassword());
        try {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.save(student);
            message[0] = "message";
            message[1] = "Uczeń " + student.getFirstName() + " "  + student.getLastName() + " został dodany prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Uczeń " + student.getFirstName() + " " + student.getLastName() + " nie został dodany.";
        }
        return message;
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

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public boolean isExist(String username){
        Admin admin = adminRepository.findByUsername(username);
        Teacher teacher = teacherRepository.findByUsername(username);
        return (admin == null && teacher == null);
    }

    public String[] remove(Long id) {
        String[] message = new String[2];
        Student student = studentRepository.findOne(id);
        try {
            studentRepository.delete(id);
            message[0] = "message";
            message[1] = "Uczeń " + student.getFirstName() + " "  + student.getLastName() + " usunięty prawidłowo.";
            } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Nie możesz usunąć ucznia " + student.getFirstName() + " "  + student.getLastName() + ", jest on powiązany z innymi elementami systemu.";
        }
        return message;
    }

    public String[] update(Student studentUpdated) {
        String[] message = new String[2];
        Student student = studentRepository.findOne(studentUpdated.getId());
        try {
            if (!student.getFirstName().equals(studentUpdated.getFirstName())) {
                studentRepository.UpdateFirsNameQuery(studentUpdated.getFirstName(), student.getId());
            }
            if (!student.getLastName().equals(studentUpdated.getLastName())) {
                studentRepository.UpdateLastNameQuery(studentUpdated.getLastName(), student.getId());
            }
            if (!student.getDescription().equals(studentUpdated.getDescription())) {
                studentRepository.UpdateDescriptionQuery(studentUpdated.getDescription(), student.getId());
            }
            if (!student.getUsername().equals(studentUpdated.getUsername())) {
                studentRepository.UpdateUsernameQuery(studentUpdated.getUsername(), student.getId());
            }
            if (student.getTeam() != studentUpdated.getTeam()) {
                studentRepository.UpdateTeamQuery(studentUpdated.getTeam(), student.getId());
            }
            // można zmienić klase mając już oceny ale te oceny nie przechodzą choć pozostają w bazie
            message[0] = "message";
            message[1] = "Uczeń " + student.getFirstName() + " "  + student.getLastName() + " został zmodyfikowany.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Uczeń " + student.getFirstName() + " "  + student.getLastName() + " nie został zmodyfikowany.";
        }
        return message;
    }

    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }
}