package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.Mark;
import pl.coderslab.entity.Student;
import pl.coderslab.repository.LessonRepository;
import pl.coderslab.repository.MarkRepository;
import pl.coderslab.repository.StudentRepository;
import pl.coderslab.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService {
    @Autowired
    MarkRepository markRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeamRepository teamRepository;

    public double userAvgInLesson(Long idLesson, Long idUser) {
        List<Mark> marks = markRepository.findAllMarkByStudentQuery(idUser, idLesson);
        Double sum = 0d;
        Double weight = 0d;
        for (Mark mark : marks) {
            sum += mark.getValue() * mark.getMarkSeries().getWeight();
            weight += mark.getMarkSeries().getWeight();
        }
        Double avg = sum/weight;
        Double avg2 = (Double.valueOf(Math.round(avg * 100))) / 100;
        return avg2;
    }


    public List<Double> userAvgsInAllLessons(Long idUser) {
        List<Lesson> lessons = lessonRepository.findAllLessonByTeamQuery(teamRepository.findOne(studentRepository.findOne(idUser).getTeam().getId()));
        List<Double> avgs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            avgs.add(userAvgInLesson(lesson.getId(),idUser));
        }
        return avgs;
    }


    public double teamAvgInLesson(Long idLesson, Long idUser) {
        List<Student> students = studentRepository.findAllByTeam(teamRepository.findOne(studentRepository.findOne(idUser).getTeam().getId()));
        List<Double> avgsStudentsInLesson = new ArrayList<>();
        for (Student student : students) {
            avgsStudentsInLesson.add(userAvgInLesson(idLesson, student.getId()));
        }
        Double sum = 0d;
        for (Double avg : avgsStudentsInLesson) {
            sum += avg;
        }
        Double avg = sum/avgsStudentsInLesson.size();
        Double avg2 = (Double.valueOf(Math.round(avg * 100))) / 100;
        return avg2;
    }


//    public List<Double> teamAvgsInAllLessons() {
//
//    }
}
