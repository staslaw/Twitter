package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Subject;
import pl.coderslab.repository.LessonRepository;
import pl.coderslab.service.LessonService;
import pl.coderslab.service.SubjectService;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/subjects")
public class AdminSubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    LessonService lessonService;

    @RequestMapping("")
    public String toSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/subjects";
    }

    @RequestMapping("/add")
    public String addSubject1(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/subjectForm";
    }

    @PostMapping("/add")
    public String addSubject2(@Valid Subject subject, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/subjectForm";
        } else {
            String[] message = subjectService.save(subject);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/admin/subjects";
        }
    }

    @RequestMapping("/remove")
    public String removeSubject(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        String[] message = subjectService.remove(id);
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        return "redirect:/admin/subjects";
    }

    @RequestMapping("/update")
    public String updateSubject1(@RequestParam Long id, Model model) {
        Subject subject = subjectService.find(id);
        model.addAttribute("subject", subject);
        return "admin/subjectForm";
    }

    @PostMapping("/update")
    public String updateSubject2(@Valid Subject subject, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/subjectForm";
        } else {
            String[] message = subjectService.update(subject);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/admin/subjects";
        }
    }

    @RequestMapping("/details")
    public String detailsSubject(@RequestParam Long id, Model model) {
        Subject subject = subjectService.find(id);
        model.addAttribute("subject", subject);
        model.addAttribute("teams", lessonService.findAllTeamsWithSubject(id));
        return "admin/subjectDetails";
    }
}