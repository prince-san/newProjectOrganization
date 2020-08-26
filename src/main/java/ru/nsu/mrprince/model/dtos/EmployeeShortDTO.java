package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeShortDTO {

    private Integer id;

    private String name;

    private LocalDate birthDate;

    private String job;

}
