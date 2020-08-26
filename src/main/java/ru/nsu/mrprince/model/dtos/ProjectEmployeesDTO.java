package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class ProjectEmployeesDTO {

    private Integer id;

    private String name;

    private Integer amountOfEmployees;

    private Map<String, Integer> amountOfEmployeesInCategories;

}
