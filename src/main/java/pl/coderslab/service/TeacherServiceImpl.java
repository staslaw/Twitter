package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Teacher;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.TeacherRepository;


@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Teacher findByTeacherName(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setEnabled(1);
        teacher.setRole(roleRepository.findOneRoleByNameQuery("ROLE_TEACHER"));
        teacherRepository.save(teacher);
    }
}