package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.*;
import pl.coderslab.repository.*;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.MarkService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    MarkSeriesRepository markSeriesRepository;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    MarkService markService;


    @RequestMapping("")
    public String home(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("student", studentRepository.findOne(currentUser.getId()));
        model.addAttribute("news", newsRepository.findAllByRole(currentUser.getRole()));
        return "student/home";
    }

    @RequestMapping("/lessons")
    public String lessons(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        Team team = studentRepository.findOne(currentUser.getId()).getTeam();
        List<Lesson> lessons = lessonRepository.findAllLessonByTeamQuery(team);
        model.addAttribute("lessons", lessons);
        model.addAttribute("avgs", markService.userAvgsInAllLessons(currentUser.getId()));

        return "student/lessons";
    }

    @RequestMapping("/marks/{idL}")
    public String marks(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long idL) {
        List<Mark> marks = markRepository.findAllMarkByStudentQuery(currentUser.getId(), idL);
        model.addAttribute("avg", markService.userAvgInLesson(idL, currentUser.getId()));
        model.addAttribute("avgTeam", markService.teamAvgInLesson(idL, currentUser.getId()));
        model.addAttribute("markSeries", markSeriesRepository.findAllMarkSeriesByLessonQuery(lessonRepository.findOne(idL)));
        model.addAttribute("marks", marks);
        model.addAttribute("student", studentRepository.findOne(currentUser.getId()));
        return "student/marks";
    }

    @RequestMapping("/profile")
    public String profile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("student", studentRepository.findOne(currentUser.getId()));
        return "student/profile";
    }
}
