package ru.nsu.mrprince.model.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjectDTO {

        private Integer id;

        private String name;

        private String supervisor;

        private LocalDate startDate;

        private LocalDate endDate;

        private Set<String> contracts;

}
