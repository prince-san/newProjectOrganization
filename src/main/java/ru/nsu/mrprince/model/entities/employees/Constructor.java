package ru.nsu.mrprince.model.entities.employees;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("constructor")
public class Constructor extends Supervisor{

    private Integer patents;

}
