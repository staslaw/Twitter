package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.Subject;
import pl.coderslab.entity.Teacher;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.LessonRepository;
import pl.coderslab.repository.SubjectRepository;
import pl.coderslab.repository.TeacherRepository;
import pl.coderslab.repository.TeamRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/teams")
public class AdminTeamController {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    LessonRepository lessonRepository;


    @RequestMapping("")
    public String toGroups() {
        return "admin/groups";
    }

    @RequestMapping("/add")
    public String addGroup1(Model model) {
        model.addAttribute("team", new Team());
        List<Teacher> list = teacherRepository.findAll();
        List<Teacher> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEducatorGroup() == null) {
                newList.add(list.get(i));
            }
        }
        model.addAttribute("teachers", newList);
        return "admin/groupForm";
    }

    @PostMapping("/add")
    public String addGroup2(Model model, @Valid Team team, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Teacher> list = teacherRepository.findAll();
            List<Teacher> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEducatorGroup() == null) {
                    newList.add(list.get(i));
                }
            }
            model.addAttribute("teachers", newList);
            return "admin/groupForm";
        } else {
            try {
                teamRepository.save(team);
                redirectAttributes.addFlashAttribute("message", "Klasa dodana prawidłowo.");
                return "redirect:/admin/teams";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Klasa nie została dodana.");
                return "redirect:/admin/teams";
            }
        }
    }

    @RequestMapping("{idT}/remove")
    public String removeGroup(@PathVariable Long idT, RedirectAttributes redirectAttributes) {
        try {
            teamRepository.delete(idT);
            redirectAttributes.addFlashAttribute("message", "Klasa dodana prawidłowo.");
            return "redirect:/admin/teams";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć klasy, jest ona powiązana z innymi elementami systemu.");
            return "redirect:/admin/teams";
        }
    }

    @RequestMapping("{idT}/update")
    public String updateGroup1(@PathVariable Long idT, Model model) {
        Team team = teamRepository.findOne(idT);
        model.addAttribute("team", team);
        List<Teacher> list = teacherRepository.findAll();
        List<Teacher> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getEducatorGroup() == null) || (list.get(i).getEducatorGroup().getId() == team.getId())) {
                newList.add(list.get(i));
            }
        }
        model.addAttribute("teachers", newList);
        return "admin/groupForm";
    }

    @PostMapping("{idT}/update")
    public String updateGroup2(@Valid Team team, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Teacher> list = teacherRepository.findAll();
            List<Teacher> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getEducatorGroup() == null) || (list.get(i).getEducatorGroup().getId() == team.getId())) {
                    newList.add(list.get(i));
                }
            }
            model.addAttribute("teachers", newList);
            return "admin/groupForm";
        } else {
            try {
                teamRepository.save(team);
                redirectAttributes.addFlashAttribute("message", "Klasa zmodyfikowana prawidłowo.");
                return "redirect:/admin/teams";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Klasa nie została zmodyfikowana.");
                return "redirect:/admin/teams";
            }
        }
    }

    @RequestMapping("/{idT}/details")
    public String details(@PathVariable Long idT, Model model) {
        Team team = teamRepository.findOne(idT);
        model.addAttribute("team", team);
        return "admin/groupDetails";
    }

    @RequestMapping("/{idT}/subjects")
    public String addSubjects(@PathVariable Long idT, Model model) {
        List<Lesson> lessons = lessonRepository.findAllLessonOfTeamQuery(teamRepository.findOne(idT));
        model.addAttribute("lessons", lessons);
        model.addAttribute("team", teamRepository.findOne(idT).getName());
        return "admin/groupLessons";
    }

    @RequestMapping("/{idT}/addLesson")
    public String addLesson(@PathVariable Long idT, Model model) {
        List<Long> idSubjectsThisTeam = lessonRepository.findAllOfIdLessonOfTeamQuery(idT);
        List<Subject> allSubjects = subjectRepository.findAllWithTeachersQuery();
        List<Subject> subjectsNew = new ArrayList<>();
        for (Subject subject : allSubjects) {
            if (!idSubjectsThisTeam.contains(subject.getId())) {
                subjectsNew.add(subject);
            }
        }
        Team team = teamRepository.findOne(idT);
        model.addAttribute("subjectsNew", subjectsNew);
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("team", team);
        return "admin/groupLessonsAdd";
    }

    @PostMapping("/{idT}/addLesson")
    public String addLesson2(@Valid Lesson lesson, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/groupLessonsAdd";
        } else {
            try {
                lessonRepository.save(lesson);
                redirectAttributes.addFlashAttribute("message", "Lekcja dodana prawidłowo.");
                return "redirect:/admin/teams/{idT}/subjects";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Lekcja nie została dodana.");
                return "redirect:/admin/teams/{idT}/subjects";
            }
        }
    }

    @RequestMapping("/{idT}/removeLesson")
    public String removeLesson(@RequestParam Long idL, RedirectAttributes redirectAttributes) {
        try {
            lessonRepository.delete(idL);
            redirectAttributes.addFlashAttribute("message", "Lekcja usunięta prawidłowo");
            return "redirect:/admin/teams/{idT}/subjects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Nie możesz usunąć lekcji, jest ona powiązana z innymi elementami systemu");
            return "redirect:/admin/teams/{idT}/subjects";
        }
    }

//    @RequestMapping("/{id}/updateLesson")
//    public String updateLesson(@RequestParam Long idL, Model model, @PathVariable Long id) {
//        model.addAttribute("lesson", lessonRepository.findOne(idL));
//        List<Long> idSubjectsThisTeam = lessonRepository.findAllOfIdLessonOfTeamQuery(id);
//        List<Subject> allSubjects = subjectRepository.findAllWithTeachersQuery();
//        List<Subject> subjectsNew = new ArrayList<>();
//        for (Subject subject : allSubjects) {
//            if (!idSubjectsThisTeam.contains(subject.getId())) {
//                subjectsNew.add(subject);
//            }
//        }
//        Team team = teamRepository.findOne(id);
//        model.addAttribute("subjectsNew", subjectsNew);
//        return "admin/groupLessonsAdd";
//    }

    @ModelAttribute("teams")
    public List<Team> groupList() {
        return teamRepository.findAll();
    }

    @ModelAttribute("subjects")
    public List<Subject> subjects() {
        return subjectRepository.findAll();
    }
}
