package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.ClassRoom;
import pl.coderslab.service.ClassRoomService;
import pl.coderslab.service.SubjectService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/classRooms")
public class AdminClassRoomController {
    @Autowired
    ClassRoomService classRoomService;
    @Autowired
    SubjectService subjectService;

    @RequestMapping("")
    public String toClassRooms(Model model) {
        model.addAttribute("classRooms", classRoomService.findAll());
        return "admin/classRooms";
    }

    @RequestMapping("/add")
    public String addClassRoom1(Model model) {
        model.addAttribute("classRoom", new ClassRoom());
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/classRoomForm";
    }

    @PostMapping("/add")
    public String addClassRoom2(@Valid ClassRoom classRoom, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subjects", subjectService.findAll());
            return "admin/classRoomForm";
        } else {
            String[] message = classRoomService.save(classRoom);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/admin/classRooms";
        }
    }

    @RequestMapping("/remove")
    public String removeClassRoom(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        String[] message = classRoomService.remove(id);
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        return "redirect:/admin/classRooms";
    }

    @RequestMapping("/update")
    public String updateClassRoom1(@RequestParam Long id, Model model) {
        ClassRoom classRoom = classRoomService.find(id);
        model.addAttribute("classRoom", classRoom);
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/classRoomForm";
    }

    @PostMapping("/update")
    public String updateClassRoom2(@Valid ClassRoom classRoom, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subjects", subjectService.findAll());
            return "admin/classRoomForm";
        } else {
            String[] message = classRoomService.update(classRoom);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/admin/classRooms";
        }
    }

    @RequestMapping("/details")
    public String detailsClassRoom(@RequestParam Long id, Model model) {
        ClassRoom classRoom = classRoomService.find(id);
        model.addAttribute("classRoom", classRoom);
        return "admin/classRoomDetails";
    }
}