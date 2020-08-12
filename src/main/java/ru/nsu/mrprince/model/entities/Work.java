package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Work extends AbstractEntity{

    private String description;

    private Double cost;

    @OneToMany
    private Set<Equipment> equipment;

    @ManyToOne
    private Subcontractor subcontractor;

    private Date startDate;

    private Date endDate;
}
