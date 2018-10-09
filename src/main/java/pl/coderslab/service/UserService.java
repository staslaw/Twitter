package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    protected MailService mailService;

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
}
