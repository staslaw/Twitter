package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.*;
import pl.coderslab.service.StudentServiceImpl;
import pl.coderslab.service.TeamService;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/students")
public class AdminStudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    TeamService teamService;

    @RequestMapping("")
    public String toStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "admin/students";
    }

    @RequestMapping("/add")
    public String addStudent1(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("groups", teamService.findAll());
        return "admin/studentForm";
    }

    @PostMapping("/add")
    public String addStudent2(@Valid Student student, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "admin/studentForm";
        } else {
            if (studentService.isExist(student.getUsername())) {
                String[] messageSave = studentService.saveStudent(student);
                redirectAttributes.addFlashAttribute(messageSave[0], messageSave[1] + " " + messageSave[2]);
                return "redirect:/admin/students";
            } else {
                redirectAttributes.addFlashAttribute("messageDanger", "Uczeń nie został dodany. Podaj inny email.");
                return "redirect:/admin/students";
            }
        }
    }

    @RequestMapping("/remove")
    public String removeStudent(@RequestParam Long id, RedirectAttributes redirectAttributes, Model model) {
        String[] message = studentService.remove(id);
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/admin/students";
    }

    @RequestMapping("/update")
    public String updateStudent1(@RequestParam Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("groups", teamService.findAll());
        return "admin/studentForm";
    }

    @PostMapping("/update")
    public String updateStudent2(@Valid Student student, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "admin/studentForm";
        } else {
            if (studentService.isExist(student.getUsername())) {
                String[] message = studentService.update(student);
                redirectAttributes.addFlashAttribute(message[0], message[1]);
                return "redirect:/admin/students";
            } else {
                redirectAttributes.addFlashAttribute("messageDanger", "Uczeń " + student.getFirstName() + " " + student.getLastName() + " nie został zmodyfikowany. Podaj inny email.");
                return "redirect:/admin/students";
            }
        }
    }

    @RequestMapping("/details")
    public String updateDetails(@RequestParam Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "admin/studentDetails";
    }
}