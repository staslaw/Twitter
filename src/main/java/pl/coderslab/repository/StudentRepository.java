package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Team;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsername(String username);

    List<Student> findAllByTeam(Team team);


    @Transactional
    @Modifying
    @Query("update Student s set s.firstName = ?1 where s.id = ?2")
    void UpdateFirsNameQuery(String firstName, Long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.lastName = ?1 where s.id = ?2")
    void UpdateLastNameQuery(String lastName, Long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.username = ?1 where s.id = ?2")
    void UpdateUsernameQuery(String username, Long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.description = ?1 where s.id = ?2")
    void UpdateDescriptionQuery(String description, Long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.team = ?1 where s.id = ?2")
    void UpdateTeamQuery(Team team, Long id);
}
