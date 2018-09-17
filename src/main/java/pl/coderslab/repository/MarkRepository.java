package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.Mark;
import pl.coderslab.entity.Student;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    @Query("select m from Mark m where m.lesson = ?1")
    List<Mark> findAllMarkByLessonQuery(Lesson lesson);

    @Query("select m from Mark m where m.student.id = ?1 and m.lesson.id = ?2")
    List<Mark> findAllMarkByStudentQuery(Long id1, Long id2);

    List<Mark> findAllByStudent(Student student);
}
