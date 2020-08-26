package ru.nsu.mrprince.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.mrprince.model.dtos.EmployeeDTO;
import ru.nsu.mrprince.services.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public void createEmployee(EmployeeDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
    }

}
