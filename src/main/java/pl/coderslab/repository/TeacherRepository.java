package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Subject;
import pl.coderslab.entity.Teacher;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t left join fetch t.subjects where t.id = ?1")
    Teacher findOneWithSubjectListQuery(Long id);

    @Query("select distinct t from Teacher t left join fetch t.subjects")
    List<Teacher> findAllWithSubjectListQuery();

    @Query("select t from Teacher t where t.subjects = ?1")
    List<Teacher> findAllWithThisSubjectsQuery(Subject subject);

    Teacher findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Teacher t set t.firstName = ?1 where t.id = ?2")
    void UpdateFirsNameQuery(String firstName, Long id);

    @Transactional
    @Modifying
    @Query("update Teacher t set t.lastName = ?1 where t.id = ?2")
    void UpdateLastNameQuery(String lastName, Long id);

    @Transactional
    @Modifying
    @Query("update Teacher t set t.username = ?1 where t.id = ?2")
    void UpdateUsernameQuery(String username, Long id);

    @Transactional
    @Modifying
    @Query("update Teacher t set t.description = ?1 where t.id = ?2")
    void UpdateDescriptionQuery(String description, Long id);

    @Transactional
    @Modifying
    @Query("update Teacher t set t.subjects = ?1 where t.id = ?2")
    void UpdateSubjectsQuery(List<Subject> subjects, Long id);

}