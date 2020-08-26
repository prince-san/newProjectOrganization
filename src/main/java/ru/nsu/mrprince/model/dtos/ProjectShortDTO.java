package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectShortDTO {

    private Integer id;

    private String name;

    private String supervisor;

    private LocalDate startDate;

    private LocalDate endDate;

}
