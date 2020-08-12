package ru.nsu.mrprince.model.entities.employees;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.mrprince.model.entities.Equipment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("technician")
public class Technician extends Employee{

    @OneToMany
    private Set<Equipment> equipment;

}
