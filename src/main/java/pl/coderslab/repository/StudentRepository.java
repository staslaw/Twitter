package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Student;
import pl.coderslab.entity.Team;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsername(String username);

    List<Student> findAllByTeam(Team team);
}
