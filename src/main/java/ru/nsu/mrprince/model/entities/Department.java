package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.employees.Employee;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Department extends AbstractEntity{

    private String name;

    @OneToOne
    private Employee leader;

    @OneToMany
    private Set<Employee> employees;

    @OneToMany
    private Set<Equipment> equipment;

}
