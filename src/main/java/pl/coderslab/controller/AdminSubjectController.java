package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Subject;
import pl.coderslab.repository.SubjectRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/subjects")
public class AdminSubjectController {
    @Autowired
    SubjectRepository subjectRepository;


    @RequestMapping("")
    public String toSubjects() {
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
            try {
                subjectRepository.save(subject);
                redirectAttributes.addFlashAttribute("message", "Przedmiot dodany prawidłowo.");
                return "redirect:/admin/subjects";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Przedmiot nie został dodany.");
                return "redirect:/admin/subjects";
            }
        }
    }

    @RequestMapping("/remove")
    public String removeSubject(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            subjectRepository.delete(id);
            redirectAttributes.addFlashAttribute("message", "Przedmiot został usunięty.");
            return "redirect:/admin/subjects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć przedmiotu, jest on powiązany z innymi elementami systemu.");
            return "redirect:/admin/subjects";
        }
    }

    @RequestMapping("/update")
    public String updateSubject1(@RequestParam Long id, Model model) {
        Subject subject = subjectRepository.findOne(id);
        model.addAttribute("subject", subject);
        return "admin/subjectForm";
    }

    @PostMapping("/update")
    public String updateSubject2(@Valid Subject subject, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/subjectForm";
        } else {
            try {
                subjectRepository.save(subject);
                redirectAttributes.addFlashAttribute("message", "Przedmiot zmodyfikowany prawidłowo.");
                return "redirect:/admin/subjects";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Przedmiot nie został zmodyfikowany.");
                return "redirect:/admin/subjects";
            }
        }
    }

    @RequestMapping("/details")
    public String detailsSubject(@RequestParam Long id, Model model) {
        Subject subject = subjectRepository.findOneWithTeamsQuery(id);
        Subject subject2 = subjectRepository.findOneWithClassRoomsQuery(id);
        Subject subject3 = subjectRepository.findOneWithTeachersQuery(id);
        model.addAttribute("subject", subject);
        model.addAttribute("subject2", subject2);
        model.addAttribute("subject3", subject3);
        return "admin/subjectDetails";
    }


    @ModelAttribute("subjects")
    public List<Subject> subjectList() {
        return subjectRepository.findAll();
    }

}
