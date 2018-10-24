package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);


    @Transactional
    @Modifying
    @Query("update User u set u.photoPath = ?1 where u.id = ?2")
    void UpdatePhotoPathQuery(String photoPath, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.username = ?1 where u.id = ?2")
    void UpdateUsernameQuery(String username, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.firstName = ?1 where u.id = ?2")
    void UpdateFirsNameQuery(String firstName, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.lastName = ?1 where u.id = ?2")
    void UpdateLastNameQuery(String lastName, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.email = ?1 where u.id = ?2")
    void UpdateEmailQuery(String email, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.dateOfBirth = ?1 where u.id = ?2")
    void UpdateDateOfBirthQuery(String dateOfBirth, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.description = ?1 where u.id = ?2")
    void UpdateDescriptionQuery(String description, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.id = ?2")
    void UpdatePasswordQuery(String password, Long id);
}
