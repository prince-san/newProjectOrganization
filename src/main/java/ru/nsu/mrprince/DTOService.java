package ru.nsu.mrprince;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.mrprince.model.dtos.EmployeeDTO;
import ru.nsu.mrprince.model.entities.employees.Employee;

@Service
@RequiredArgsConstructor
public class DTOService {

    private final ModelMapper modelMapper;

    public EmployeeDTO mapEmployeeToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

}
