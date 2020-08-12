package ru.nsu.mrprince.model.entities.employees;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("assistant")
public class Assistant extends Employee {

}
