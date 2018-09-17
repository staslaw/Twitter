package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.Team;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l left join fetch l.team where l.team = ?1")
    List<Lesson> findAllLessonOfTeamQuery(Team team);

    @Query("select l.subject.id from Lesson l where l.team.id = ?1")
    List<Long> findAllOfIdLessonOfTeamQuery(Long id);

    @Query("select l from Lesson l where l.teacher.id = ?1")
    List<Lesson> findAllLessonOfTeacherQuery(Long id);

    @Query("select l from Lesson l where l.team = ?1")
    List<Lesson> findAllLessonByTeamQuery(Team team);
}
