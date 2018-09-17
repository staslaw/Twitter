package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.ClassRoom;
import pl.coderslab.entity.Subject;
import pl.coderslab.repository.ClassRoomRepository;
import pl.coderslab.repository.SubjectRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/classRooms")
public class AdminClassRoomController {
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping("")
    public String toClassRooms() {
        return "admin/classRooms";
    }

    @RequestMapping("/add")
    public String addClassRoom1(Model model) {
        model.addAttribute("classRoom", new ClassRoom());
        return "admin/classRoomForm";
    }

    @PostMapping("/add")
    public String addClassRoom2(@Valid ClassRoom classRoom, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/classRoomForm";
        } else {
            try {
                classRoomRepository.save(classRoom);
                redirectAttributes.addFlashAttribute("message", "Sala dodana prawidłowo.");
                return "redirect:/admin/classRooms";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Sala nie została dodana.");
                return "redirect:/admin/classRooms";
            }
        }
    }

    @RequestMapping("/remove")
    public String removeClassRoom(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            classRoomRepository.delete(id);
            redirectAttributes.addFlashAttribute("message", "Sala została usunięta.");
            return "redirect:/admin/classRooms";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć sali, jest ona powiązana z innymi elementami systemu.");
            return "redirect:/admin/classRooms";
        }
    }

    @RequestMapping("/update")
    public String updateClassRoom1(@RequestParam Long id, Model model) {
        ClassRoom classRoom = classRoomRepository.findOne(id);
        model.addAttribute("classRoom", classRoom);
        return "admin/classRoomForm";
    }

    @PostMapping("/update")
    public String updateClassRoom2(@Valid ClassRoom classRoom, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/classRoomForm";
        } else {
            try {
                classRoomRepository.save(classRoom);
                redirectAttributes.addFlashAttribute("message", "Sala zmodyfikowana prawidłowo.");
                return "redirect:/admin/classRooms";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Sala nie została zmodyfikowana.");
                return "redirect:/admin/classRooms";
            }
        }
    }

    @RequestMapping("/details")
    public String detailsClassRoom(@RequestParam Long id, Model model) {
        ClassRoom classRoom = classRoomRepository.findOne(id);
        model.addAttribute("classRoom", classRoom);
        return "admin/classRoomDetails";
    }


    @ModelAttribute("classRooms")
    public List<ClassRoom> classRoomList() {
        return classRoomRepository.findAll();
    }

    @ModelAttribute("subjects")
    public List<Subject> subjectsList() {
        return subjectRepository.findAll();
    }
}
