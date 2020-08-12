package ru.nsu.mrprince.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.mrprince.DTOService;
import ru.nsu.mrprince.model.dtos.EmployeeDTO;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.repositories.EmployeeRepository;

import javax.persistence.DiscriminatorValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    private final DTOService dtoService;

    @GetMapping("/employees")
    List<EmployeeDTO> all() {
        ArrayList<EmployeeDTO> result = new ArrayList<>();

        for (Employee employee : repository.findAll()) {
            EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
            employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
            result.add(employeeDTO);
        }

        return result;
    }

    @GetMapping("/employees/{job}")
    List<Employee> employeesByJob(@PathVariable("job") String job) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee employee : repository.findAll()) {
            if (employee.getClass().getAnnotation(DiscriminatorValue.class).value().equals(job)) {
                result.add(employee);
            }
        }
        return result;
    }

}
