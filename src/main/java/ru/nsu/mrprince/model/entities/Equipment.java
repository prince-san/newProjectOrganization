package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.employees.Technician;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Getter
@Setter
@Entity
public class Equipment extends AbstractEntity{

    private String name;

    @ManyToOne
    private Technician technician;

    @ManyToOne
    private Department department;

    @ManyToMany
    private Set<Work> works;

}
