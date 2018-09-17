package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Lesson;
import pl.coderslab.entity.MarkSeries;
import pl.coderslab.entity.Team;

import java.util.List;

public interface MarkSeriesRepository extends JpaRepository<MarkSeries, Long> {

    @Query("select m from MarkSeries m where m.lesson = ?1")
    List<MarkSeries> findAllMarkSeriesByLessonQuery(Lesson lesson);
}
