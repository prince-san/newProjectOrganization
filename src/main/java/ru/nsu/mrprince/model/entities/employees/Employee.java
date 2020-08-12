package ru.nsu.mrprince.model.entities.employees;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.AbstractEntity;
import ru.nsu.mrprince.model.entities.Contract;
import ru.nsu.mrprince.model.entities.Department;
import ru.nsu.mrprince.model.entities.Project;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "job", discriminatorType = DiscriminatorType.STRING)
public class Employee extends AbstractEntity {

    private String name;

    private LocalDate birthDate;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Contract contract;

}
