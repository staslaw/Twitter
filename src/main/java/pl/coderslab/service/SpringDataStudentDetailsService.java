package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.entity.Admin;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Teacher;

import java.util.HashSet;
import java.util.Set;

public class SpringDataStudentDetailsService implements UserDetailsService {
    private StudentService studentService;
    private TeacherService teacherService;
    private AdminService adminService;

    @Autowired
    public void setUserRepository(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setTeacherRepository(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setAdminRepository(AdminService adminRepository) {
        this.adminService = adminRepository;
    }

    /**
     * @// TODO: 12.09.18
     * @param username
     * @return
     */

    @Override
    public UserDetails loadUserByUsername(String username) {
        Teacher teacher = teacherService.findByTeacherName(username);
        Student student = studentService.findByStudentName(username);
        Admin admin = adminService.findByAdminName(username);

        if (admin == null) {
            if (student == null) {
                if (teacher == null) {
                    throw new UsernameNotFoundException(username);
                } else {
                    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(teacher.getRole().getName()));
                    return new CurrentUser(teacher.getUsername(), teacher.getPassword(), teacher.getId(),
                            teacher.getRole(), grantedAuthorities);
                }
            } else {
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(student.getRole().getName()));
                return new CurrentUser(student.getUsername(), student.getPassword(), student.getId(),
                        student.getRole(), grantedAuthorities);
            }
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(admin.getRole().getName()));
            return new CurrentUser(admin.getUsername(), admin.getPassword(), admin.getId(),
                    admin.getRole(), grantedAuthorities);
        }
    }
}