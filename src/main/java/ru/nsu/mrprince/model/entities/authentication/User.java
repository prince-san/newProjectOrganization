package ru.nsu.mrprince.model.entities.authentication;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AbstractEntity {

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

}
