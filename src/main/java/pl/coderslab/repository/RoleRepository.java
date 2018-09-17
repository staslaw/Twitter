package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name = ?1")
    Role findOneRoleByNameQuery(String str);
}
