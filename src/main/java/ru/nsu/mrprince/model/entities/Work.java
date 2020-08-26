package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Work extends AbstractEntity{

    private String description;

    private Double cost;

    @ManyToMany
    private Set<Equipment> equipment;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Subcontractor subcontractor;

    private LocalDate startDate;

    private LocalDate endDate;
}
