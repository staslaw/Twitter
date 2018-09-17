package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select g from Team g left join fetch g.students where g.id = ?1")
    Team findOneWithStudentsQuery(Long id);

    @Query("select g from Team g left join fetch g.subjects where g.id = ?1")
    Team findOneWithSubjectsQuery(Long id);

    @Query("select distinct g from Team g left join fetch g.students")
    List<Team> findAllWithStudentListQuery();

}
