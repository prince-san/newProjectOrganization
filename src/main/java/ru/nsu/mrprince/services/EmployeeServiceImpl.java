package ru.nsu.mrprince.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.mrprince.DTOService;
import ru.nsu.mrprince.model.dtos.EmployeeDTO;
import ru.nsu.mrprince.repositories.EmployeeRepository;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DTOService dtoService;

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(dtoService.mapDtoToEmployee(employeeDTO));
    }
}
