package ru.nsu.mrprince;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.mrprince.model.dtos.*;
import ru.nsu.mrprince.model.entities.Contract;
import ru.nsu.mrprince.model.entities.Equipment;
import ru.nsu.mrprince.model.entities.Project;
import ru.nsu.mrprince.model.entities.Work;
import ru.nsu.mrprince.model.entities.employees.Employee;

@Service
@RequiredArgsConstructor
public class DTOService {

    private final ModelMapper modelMapper;

    public EmployeeDTO mapEmployeeToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public ProjectDTO mapProjectToDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public ContractDTO mapContractToDto(Contract contract) {
        return modelMapper.map(contract, ContractDTO.class);
    }

    public ProjectShortDTO mapProjectToShortDto(Project project) {
        return modelMapper.map(project, ProjectShortDTO.class);
    }

    public ContractShortDTO mapContractToShortDto(Contract contract) {
        return modelMapper.map(contract, ContractShortDTO.class);
    }

    public ProjectCostDTO mapProjectToCostDto(Project project) {
        return modelMapper.map(project, ProjectCostDTO.class);
    }

    public ContractCostDTO mapContractToCostDto(Contract contract) {
        return modelMapper.map(contract, ContractCostDTO.class);
    }

    public EquipmentDTO mapEqipmentToDto(Equipment equipment) {
        return modelMapper.map(equipment, EquipmentDTO.class);
    }

    public WorkDTO mapWorkToDto(Work work) {
        return modelMapper.map(work, WorkDTO.class);
    }

    public ProjectEmployeesDTO mapProjectToEmployeesDto(Project project) {
        return modelMapper.map(project, ProjectEmployeesDTO.class);
    }

    public EfficiencyDTO mapEquipmentToEfficiencyDto(Equipment equipment) {
        return modelMapper.map(equipment, EfficiencyDTO.class);
    }

    public EfficiencyDTO mapProjectToEfficiencyDto(Project project) {
        return modelMapper.map(project, EfficiencyDTO.class);
    }

    public EfficiencyDTO mapContractToEfficiencyDto(Contract contract) {
        return modelMapper.map(contract, EfficiencyDTO.class);
    }

    public Employee mapDtoToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
    }
}
