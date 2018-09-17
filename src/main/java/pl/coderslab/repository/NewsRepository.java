package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.News;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.Teacher;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

//    @Query("select n from News n where n.role = ?1")
    List<News> findAllByRole(Role role);
}
