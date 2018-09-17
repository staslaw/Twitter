package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Admin;
import pl.coderslab.entity.Role;
import pl.coderslab.repository.AdminRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.service.AdminService;
import pl.coderslab.service.AdminServiceImpl;
import pl.coderslab.service.CurrentUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Controller
@RequestMapping("/")
public class StartController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminServiceImpl adminService;

    @RequestMapping("")
    public String start() {
        if (roleRepository.findOneRoleByNameQuery("ROLE_STUDENT") == null) {
            roleRepository.save(new Role("ROLE_STUDENT"));
        }
        if (roleRepository.findOneRoleByNameQuery("ROLE_TEACHER") == null) {
            roleRepository.save(new Role("ROLE_TEACHER"));
        }
        if (roleRepository.findOneRoleByNameQuery("ROLE_ADMIN") == null) {
            Admin admin = new Admin("admin@admin", "admin", roleRepository.findOne(3l), 1);
            adminService.saveAdmin(admin);
        }
        return "index";
    }

    @PostMapping("start")
    public String hello(@AuthenticationPrincipal CurrentUser currentUser) {
        String role = currentUser.getRole().getName();
        if (role.equals("ROLE_STUDENT")) {
            return "redirect:/student/";
        } else if (role.equals("ROLE_TEACHER")) {
            return "redirect:/teacher/";
        } else {
            return "redirect:/admin";
        }
    }
}