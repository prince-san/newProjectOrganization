package ru.nsu.mrprince.repositories.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.authentication.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
