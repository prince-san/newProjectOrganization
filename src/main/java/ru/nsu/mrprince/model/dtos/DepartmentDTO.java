package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class DepartmentDTO {

    private Integer id;

    private String name;

    private EmployeeShortDTO leader;

}
