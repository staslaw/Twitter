package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.*;
import pl.coderslab.repository.*;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.StudentServiceImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/students")
public class AdminStudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("")
    public String toStudents() {
        return "admin/students";
    }

    @RequestMapping("/add")
    public String addStudent1(Model model) {
        model.addAttribute("student", new Student());
        return "admin/studentForm";
    }

    @PostMapping("/add")
    public String addStudent2(@Valid Student student, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/studentForm";
        } else {
            Admin admin = adminRepository.findByUsername(student.getUsername());
            Teacher teacher = teacherRepository.findByUsername(student.getUsername());
            if (admin == null && teacher == null) {
                try {
                    studentService.saveStudent(student);
                    redirectAttributes.addFlashAttribute("message", "Uczeń dodany prawidłowo.");
                    return "redirect:/admin/students";
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("messageDanger", "Uczeń nie został dodany.");
                    return "redirect:/admin/students";
                }
            } else {
                redirectAttributes.addFlashAttribute("messageDanger", "Uczeń nie został dodany. Podaj inny email.");
                return "redirect:/admin/students";
            }
        }
    }

    @RequestMapping("/remove")
    public String removeStudent(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
        studentRepository.delete(id);
            redirectAttributes.addFlashAttribute("message", "Uczeń usunięty prawidłowo.");
            return "redirect:/admin/students";
    } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć ucznia, jest on powiązany z innymi elementami systemu.");
            return "redirect:/admin/students";
        }
    }

    @RequestMapping("/update")
    public String updateStudent1(@RequestParam Long id, Model model) {
        Student student = studentRepository.findOne(id);
        model.addAttribute("student", student);
        return "admin/studentForm";
    }

    @PostMapping("/update")
    public String updateStudent2(@Valid Student student, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/studentForm";
        } else {
            Admin admin = adminRepository.findByUsername(student.getUsername());
            Teacher teacher = teacherRepository.findByUsername(student.getUsername());
            if (admin == null && teacher == null) {
                try {
                    studentService.saveStudent(student);
                    redirectAttributes.addFlashAttribute("message", "Uczeń zmodyfikowany prawidłowo.");
                    return "redirect:/admin/students";
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("messageDanger", "Uczeń nie został zmodyfikowany.");
                    return "redirect:/admin/students";
                }
            } else {
                redirectAttributes.addFlashAttribute("messageDanger", "Uczeń nie został zmodyfikowany. Podaj inny email.");
                return "redirect:/admin/students";
            }
        }
    }

    @RequestMapping("/details")
    public String updateDetails(@RequestParam Long id, Model model) {
        Student student = studentRepository.findOne(id);
        model.addAttribute("student", student);
        return "admin/studentDetails";
    }


    @ModelAttribute("students")
    public List<Student> studentList() {
        return studentRepository.findAll();
    }

    @ModelAttribute("groups")
    public List<Team> groupList() {
        return teamRepository.findAll();
    }
}
