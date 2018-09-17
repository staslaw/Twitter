package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s left join fetch s.teams where s.id = ?1")
    Subject findOneWithTeamsQuery(Long id);

    @Query("select s from Subject s left join fetch s.classRooms where s.id = ?1")
    Subject findOneWithClassRoomsQuery(Long id);

    @Query("select s from Subject s left join fetch s.teachers where s.id = ?1")
    Subject findOneWithTeachersQuery(Long id);

    @Query("select distinct s from Subject s join fetch s.teachers")
    List<Subject> findAllWithTeachersQuery();

//    @Query("select s.id from Subject s join fetch s.teachers")
//    List<Long> findAllId();
}
