package ru.nsu.mrprince.model.entities.employees;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.Project;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Supervisor extends Employee{

    @OneToMany
    private Set<Project> projects;

}
