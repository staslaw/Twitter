package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Subject;
import pl.coderslab.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t left join fetch t.subjects where t.id = ?1")
    Teacher findOneWithSubjectListQuery(Long id);

    @Query("select distinct t from Teacher t left join fetch t.subjects")
    List<Teacher> findAllWithSubjectListQuery();

    @Query("select t from Teacher t where t.subjects = ?1")
    List<Teacher> findAllWithThisSubjectsQuery(Subject subject);

    Teacher findByUsername(String username);
}
