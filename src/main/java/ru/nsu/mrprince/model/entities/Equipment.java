package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.employees.Technician;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Equipment extends AbstractEntity{

    private String name;

    @ManyToOne
    private Technician technician;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Project project;

}
