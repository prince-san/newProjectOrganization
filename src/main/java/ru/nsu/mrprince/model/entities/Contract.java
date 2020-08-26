package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.model.entities.employees.Supervisor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Contract extends AbstractEntity{

    private String name;

    @ManyToMany
    private Set<Project> projects;

    @OneToMany
    private Set<Employee> employees;

    @ManyToOne
    private Supervisor supervisor;

    private LocalDate startDate;

    private LocalDate endDate;

}
