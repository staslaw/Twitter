package pl.coderslab.service;

import pl.coderslab.entity.User;

public interface UserServiceInterface {
    User findByUsername(String username);

    String[] saveUser(User student);
}
