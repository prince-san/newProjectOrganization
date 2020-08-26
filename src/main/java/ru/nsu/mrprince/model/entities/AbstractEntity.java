package ru.nsu.mrprince.model.entities;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    @NonNull
    private Integer id;

}
