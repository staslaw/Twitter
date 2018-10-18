package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    protected MailService mailService;
    private static String UPLOADED_FOLDER = "src/main/webapp/images/";

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String[] saveUser(User user) {
        String[] message = new String[3];
        user.setEnabled(1);
        user.setRole(roleRepository.findOneRoleByNameQuery("ROLE_USER"));
        user.setPhotoPath("/images/avatar.jpg");
//        message[2] = mailService.generateAndSendEmail(student.getUsername(), student.getUsername(), student.getPassword());
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            message[0] = "message";
            message[1] = "Rejestracja przebiegła pomyślnie";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Rejestracja nie powiodła się";
        }
        return message;
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public boolean isExist(String email) {
        if (userRepository.findByEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String[] updatePhoto(Long id, MultipartFile file) {
        String[] message = new String[2];
        if (file.isEmpty()) {
            message[0] = "messageDanger";
            message[1] = "you didn't choose foto";
        } else {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "id" + id + ".jpg");
                Files.write(path, bytes);
                userRepository.UpdatePhotoPathQuery("/images/id" + id + ".jpg", id);
                message[0] = "message";
                message[1] = "profile photo was added";
            } catch (Exception e) {
                message[0] = "messageDanger";
                message[1] = "profile photo was not added";
            }
        }
        return message;
    }

    public String[] removePhoto(Long id) {
        String[] message = new String[2];
        try {
            userRepository.UpdatePhotoPathQuery("/images/avatar.jpg", id);
            message[0] = "message";
            message[1] = "profile photo was removed";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "profile photo was not removed";
        }
        return message;
    }

    public String[] update(User user) {
        String[] message = new String[2];
        try {
//            User currentUser = userRepository.findOne(user.getId());
//            System.out.println(user.getEmail());
//            System.out.println(currentUser.getEmail());
//            System.out.println(!user.getEmail().equals(currentUser.getEmail()));
//            System.out.println(userRepository.findByEmail(user.getEmail()) == null);
//
//            if (!user.getEmail().equals(userRepository.findOne(user.getId()).getEmail())) {
//                if (userRepository.findByEmail(user.getEmail()) == null) {
//                    userRepository.UpdateEmailQuery(user.getEmail(), user.getId());
//                } else {
//                    message[0] = "messageDanger";
//                    message[1] = "try with other email";
//                    return message;
//                }
//            }
//            userRepository.UpdateFirsNameQuery(user.getFirstName(), user.getId());
//            userRepository.UpdateLastNameQuery(user.getLastName(), user.getId());
//            userRepository.UpdateDateOfBirthQuery(user.getDateOfBirth(), user.getId());
            userRepository.UpdateDescriptionQuery(user.getDescription(), user.getId());
            message[0] = "message";
            message[1] = "profile was edited";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "profile was not edited";
        }
        return message;
    }


}
