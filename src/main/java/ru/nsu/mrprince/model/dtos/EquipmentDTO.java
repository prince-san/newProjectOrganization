package ru.nsu.mrprince.model.dtos;

import lombok.Data;

@Data
public class EquipmentDTO {

    private Integer id;

    private String name;

    private String technician;

    private String department;

    private String currentWork;

}
