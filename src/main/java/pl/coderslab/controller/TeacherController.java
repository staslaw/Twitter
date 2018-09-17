package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.Mark;
import pl.coderslab.entity.MarkSeries;
import pl.coderslab.repository.*;
import pl.coderslab.service.CurrentUser;
import javax.validation.Valid;

@Controller
@RequestMapping("/teacher")
//@Slf4j
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    MarkSeriesRepository markSeriesRepository;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    NewsRepository newsRepository;

    @RequestMapping("")
    public String home(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("teacher", teacherRepository.findOne(currentUser.getId()));
        model.addAttribute("news", newsRepository.findAllByRole(currentUser.getRole()));
        return "teacher/home";
    }

    @RequestMapping("/lessons")
    public String lessons(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("lessons", lessonRepository.findAllLessonOfTeacherQuery(currentUser.getId()));
        return "teacher/lessons";
    }

    @RequestMapping("/marks/{idL}")
    public String marks(@PathVariable Long idL, Model model) {
        Lesson lesson = lessonRepository.findOne(idL);
        model.addAttribute("lesson", lesson);
        model.addAttribute("students", lesson.getTeam().getStudents());
        model.addAttribute("markSeries", markSeriesRepository.findAllMarkSeriesByLessonQuery(lesson));
        model.addAttribute("mark", new Mark());
        model.addAttribute("marksAll", markRepository.findAllMarkByLessonQuery(lesson));
        return "teacher/marks";
    }

    @PostMapping("/marks/{idL}")
    public String addMark(@Valid Mark mark, BindingResult result, @PathVariable Long idL, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Lesson lesson = lessonRepository.findOne(idL);
            model.addAttribute("lesson", lesson);
            model.addAttribute("students", lesson.getTeam().getStudents());
            model.addAttribute("markSeries", markSeriesRepository.findAllMarkSeriesByLessonQuery(lesson));
            model.addAttribute("mark", new Mark());
            model.addAttribute("marksAll", markRepository.findAllMarkByLessonQuery(lesson));
            return "teacher/marks";
        } else {
            try {
                markRepository.save(mark);
                redirectAttributes.addFlashAttribute("message", "Ocena dodana prawidłowo.");
                return "redirect:/teacher/marks/{idL}";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Ocena nie została dodana.");
                return "redirect:/teacher/marks/{idL}";
            }
        }
    }

    @RequestMapping("/marks/{idL}/addSeries")
    public String addMarksSeries(@PathVariable Long idL, Model model) {
        model.addAttribute("lesson", lessonRepository.findOne(idL));
        model.addAttribute("markSeries", new MarkSeries());
        return "teacher/addSeries";
    }

    @PostMapping("/marks/{idL}/addSeries")
    public String addMarksSeries2(@Valid MarkSeries markSeries, BindingResult result, @PathVariable Long idL, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("lesson", lessonRepository.findOne(idL));
            return "teacher/addSeries";
        } else {
            try {
                markSeriesRepository.save(markSeries);
                redirectAttributes.addFlashAttribute("message", "Seria ocen dodana prawidłowo.");
                return "redirect:/teacher/marks/{idL}";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Seria ocen nie została dodana.");
                return "redirect:/teacher/marks/{idL}";
            }
        }
    }

    @RequestMapping("/marks/{idL}/remove/{idS}")
    public String removeSeries(@PathVariable Long idS, RedirectAttributes redirectAttributes) {
        try {
            markSeriesRepository.delete(idS);
            redirectAttributes.addFlashAttribute("message", "Seria ocen usunięta prawidłowo.");
            return "redirect:/teacher/marks/{idL}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć serii ocen, jest ona powiązana z innymi elementami systemu.");
            return "redirect:/teacher/marks/{idL}";
        }
    }

    @RequestMapping("/marks/{idL}/update/{idS}")
    public String updateSeries(@PathVariable Long idS, Model model, @PathVariable Long idL) {
        model.addAttribute("lesson", lessonRepository.findOne(idL));
        model.addAttribute("markSeries", markSeriesRepository.findOne(idS));
        return "teacher/addSeries";
    }

    @PostMapping("/marks/{idL}/update/{idS}")
    public String updateSeries2(@Valid MarkSeries markSeries, BindingResult result, @PathVariable Long idL, Model model, RedirectAttributes redirectAttributes, @PathVariable Long idS) {
        if (result.hasErrors()) {
            model.addAttribute("lesson", lessonRepository.findOne(idL));
            return "teacher/addSeries";
        } else {
            try {
                markSeries.setId(idS);
                markSeriesRepository.save(markSeries);
                redirectAttributes.addFlashAttribute("message", "Seria ocen została zmodyfikowana.");
                return "redirect:/teacher/marks/{idL}";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Seria ocen nie została zmodyfikowana.");
                return "redirect:/teacher/marks/{idL}";
            }
        }
    }

    @RequestMapping("/marks/details/{idM}")
    public String markDetails(@PathVariable Long idM, Model model) {
        model.addAttribute("mark", markRepository.findOne(idM));
        return "teacher/markDetails";
    }

    @RequestMapping("/profile")
    public String profile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("teacher", teacherRepository.findOne(currentUser.getId()));
        return "teacher/profile";
    }

    @RequestMapping("/marks/details/update/{markId}/{newMark}")
    public String updateMark(@PathVariable Long markId, @PathVariable int newMark, Model model) {
        Mark mark = markRepository.findOne(markId);
        mark.setValue(newMark);
        markRepository.save(mark);
//        model.addAttribute("idM", markId);
        return "redirect:/teacher/marks/details/"+ markId;
    }

    @RequestMapping("/marks/{idL}/removeMark/{idM}")
    public String remove(@PathVariable Long idM, RedirectAttributes redirectAttributes, Model model) {
//        model.addAttribute("idL", markRepository.findOne(idM).getLesson().getId());
        try {
            markRepository.delete(idM);
            redirectAttributes.addFlashAttribute("message", "Ocena usunięta prawidłowo.");
            return "redirect:/teacher/marks/{idL}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "Nie możesz usunąć oceny, jest ona powiązana z innymi elementami systemu.");
            return "redirect:/teacher/marks/{idL}";
        }
    }

    @RequestMapping("/marks/{idL}/updateMark/{idM}")
    public String update(@PathVariable Long idM, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("mark", markRepository.findOne(idM));
        return "teacher/updateMark";
    }
}
