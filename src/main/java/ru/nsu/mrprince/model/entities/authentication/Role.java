package ru.nsu.mrprince.model.entities.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
