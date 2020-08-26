package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class WorkDTO {

    private Integer id;

    private String description;

    private Double cost;

    private Set<String> equipment;

    private String project;

    private String subcontractor;

    private LocalDate startDate;

    private LocalDate endDate;

}
