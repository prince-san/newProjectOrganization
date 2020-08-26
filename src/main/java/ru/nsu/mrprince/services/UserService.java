package ru.nsu.mrprince.services;

import ru.nsu.mrprince.model.entities.authentication.User;

import java.util.List;

public interface UserService {

    User findUserById(Integer userId);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Integer userId);

}
