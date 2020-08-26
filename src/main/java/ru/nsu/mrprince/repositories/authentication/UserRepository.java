package ru.nsu.mrprince.repositories.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.authentication.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String userName);
}
