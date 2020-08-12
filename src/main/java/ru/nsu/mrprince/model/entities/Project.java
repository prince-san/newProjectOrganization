package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.model.entities.employees.Supervisor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project extends AbstractEntity{

    private String name;

    @ManyToOne
    private Supervisor supervisor;

    @OneToMany
    private Set<Employee> employees;

    @OneToMany
    private Set<Work> works;

    @ManyToMany
    private Set<Contract> contracts;

    private Date startDate;

    private Date endDate;

}
