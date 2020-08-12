package ru.nsu.mrprince.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Subcontractor extends AbstractEntity{

    @OneToMany
    private Set<Work> works;

}
