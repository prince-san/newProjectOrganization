package ru.nsu.mrprince.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.mrprince.DTOService;
import ru.nsu.mrprince.model.dtos.*;
import ru.nsu.mrprince.model.entities.*;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.repositories.*;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/queries")
public class QueryController {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final ProjectRepository projectRepository;

    private final ContractRepository contractRepository;

    private final EquipmentRepository equipmentRepository;

    private final WorkRepository workRepository;

    private final DTOService dtoService;

    @GetMapping("/employees")
    Page<EmployeeDTO> allEmployees(Pageable pageable) {
        ArrayList<EmployeeDTO> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll(pageable)) {
            EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
            employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
            list.add(employeeDTO);
        }
        return new PageImpl<EmployeeDTO>(list);
    }

    //should make it to page
    @GetMapping("/employees/by-job")
    List<EmployeeDTO> employeesByJob(@RequestParam(name = "job") String job, Pageable pageable) {
        ArrayList<EmployeeDTO> result = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll(pageable)) {
            if (employee.getClass().getAnnotation(DiscriminatorValue.class).value().equals(job)) {
                EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
                employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
                result.add(employeeDTO);
            }
        }
        return result;
    }

    @GetMapping("/employees/by-age")
    Page<EmployeeDTO> employeesByAge(@RequestParam(name = "age") Integer age, Pageable pageable) {
        List<EmployeeDTO> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAllByBirthDateBetween(LocalDate.now().minusYears(age + 1).plusDays(1), LocalDate.now().minusYears(age), pageable)) {
            EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
            employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
            list.add(employeeDTO);
        }
        return new PageImpl<EmployeeDTO>(list);
    }

    @GetMapping("/employees/by-department/by-age")
    Page<EmployeeDTO> employeesByDepartmentAndAge(@RequestParam(name = "department-id") Integer departmentId, @RequestParam(name = "age") Integer age, Pageable pageable) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Could not find department with that ID"));

        List<EmployeeDTO> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAllByDepartmentAndBirthDateBetween(department, LocalDate.now().minusYears(age + 1).plusDays(1), LocalDate.now().minusYears(age), pageable)) {
            EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
            employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
            list.add(employeeDTO);
        }
        return new PageImpl<EmployeeDTO>(list);

    }

    @GetMapping("/employees/by-department/by-job")
    List<EmployeeDTO> employeesByDepartmentAndJob(@RequestParam(name = "department-id") Integer departmentId, @RequestParam(name = "job") String job, Pageable pageable) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Could not find department with that ID"));

        List<EmployeeDTO> result = new ArrayList<>();
        for (Employee employee : employeeRepository.findAllByDepartment(department, pageable)) {
            if (employee.getClass().getAnnotation(DiscriminatorValue.class).value().equals(job)) {
                EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(employee);
                employeeDTO.setJob(employee.getClass().getAnnotation(DiscriminatorValue.class).value());
                result.add(employeeDTO);
            }
        }
        return result;
    }

    @GetMapping("/employees/department-leaders")
    Page<EmployeeDTO> departmentLeaders(Pageable pageable) {

        Page<Department> departments = departmentRepository.findAll(pageable);

        List<EmployeeDTO> employees = new ArrayList<>();

        for (Department department : departments) {
            EmployeeDTO employeeDTO = dtoService.mapEmployeeToDto(department.getLeader());
            employeeDTO.setJob(department.getLeader().getClass().getAnnotation(DiscriminatorValue.class).value());
            employees.add(employeeDTO);
        }

        return new PageImpl<EmployeeDTO>(employees);
    }

    @GetMapping("/projects")
    Page<ProjectDTO> projects(Pageable pageable) {

        List<ProjectDTO> projects = new ArrayList<>();

        for (Project project : projectRepository.findAll(pageable)) {
            projects.add(dtoService.mapProjectToDto(project));
        }

        return new PageImpl<ProjectDTO>(projects);

    }

    @GetMapping("/contracts")
    Page<ContractShortDTO> contracts(Pageable pageable) {

        List<ContractShortDTO> contracts = new ArrayList<>();

        for (Contract contract : contractRepository.findAll(pageable)) {
            contracts.add(dtoService.mapContractToShortDto(contract));
        }

        return new PageImpl<ContractShortDTO>(contracts);

    }

    @GetMapping("/projects/now")
    Page<ProjectDTO> projectsNow(Pageable pageable) {

        List<ProjectDTO> projects = new ArrayList<>();

        for (Project project : projectRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDate.now(), LocalDate.now(),pageable)) {
            projects.add(dtoService.mapProjectToDto(project));
        }

        return new PageImpl<ProjectDTO>(projects);

    }

    @GetMapping("/projects/between")
    Page<ProjectDTO> projectsBetween(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end, Pageable pageable) {

        List<ProjectDTO> projects = new ArrayList<>();

        for (Project project : projectRepository.findAllByStartDateBetweenOrStartDateBeforeAndEndDateAfter(LocalDate.parse(start), LocalDate.parse(end), LocalDate.parse(start), LocalDate.parse(start), pageable)) {
            projects.add(dtoService.mapProjectToDto(project));
        }

        return new PageImpl<ProjectDTO>(projects);

    }

    @GetMapping("/projects/contracts")
    Page<ContractShortDTO> projectContracts(@RequestParam(name = "project-id") Integer projectId, Pageable pageable) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Could not find project with that ID"));

        List<ContractShortDTO> contracts = new ArrayList<>();
        for (Contract contract : project.getContracts()) {
            contracts.add(dtoService.mapContractToShortDto(contract));
        }

        return new PageImpl<ContractShortDTO>(contracts);

    }

    @GetMapping("/contracts/now")
    Page<ContractDTO> contractsNow(Pageable pageable) {

        List<ContractDTO> contracts = new ArrayList<>();

        for (Contract contract : contractRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDate.now(), LocalDate.now(),pageable)) {
            contracts.add(dtoService.mapContractToDto(contract));
        }

        return new PageImpl<ContractDTO>(contracts);

    }

    @GetMapping("/contracts/between")
    Page<ContractDTO> contractsBetween(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end, Pageable pageable) {

        List<ContractDTO> contracts = new ArrayList<>();

        for (Contract contract : contractRepository.findAllByStartDateBetweenOrStartDateBeforeAndEndDateAfter(LocalDate.parse(start), LocalDate.parse(end), LocalDate.parse(start), LocalDate.parse(start), pageable)) {
            contracts.add(dtoService.mapContractToDto(contract));
        }

        return new PageImpl<ContractDTO>(contracts);

    }

    @GetMapping("/contracts/projects")
    Page<ProjectShortDTO> contractProjects(@RequestParam(name = "contract-id") Integer contractId, Pageable pageable) {

        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new IllegalArgumentException("Could not find contract with that ID"));

        List<ProjectShortDTO> projects = new ArrayList<>();
        for (Project project : contract.getProjects()) {
                projects.add(dtoService.mapProjectToShortDto(project));
            }

        return new PageImpl<ProjectShortDTO>(projects);

    }

    @GetMapping("/projects/cost/between")
    Page<ProjectCostDTO> projectCosts(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end, Pageable pageable) {

        Page<Project> projects = projectRepository.findAllByEndDateBetween(LocalDate.parse(start), LocalDate.parse(end), pageable);

        List<ProjectCostDTO> projectCostDTOS = new ArrayList<>();

        for (Project project : projects) {
            double cost = 0.0;
            for (Work work : project.getWorks()) {
                if (work.getEndDate().isAfter(LocalDate.parse(start)) && work.getEndDate().isBefore(LocalDate.parse(end))) {
                    cost += work.getCost();
                }
            }
            ProjectCostDTO projectCostDTO = dtoService.mapProjectToCostDto(project);
            projectCostDTO.setCost(cost);
            projectCostDTOS.add(projectCostDTO);
        }

        return new PageImpl<ProjectCostDTO>(projectCostDTOS);
    }

    @GetMapping("/contracts/cost/between")
    Page<ContractCostDTO> contractCosts(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end, Pageable pageable) {

        Page<Contract> contracts = contractRepository.findAllByEndDateBetween(LocalDate.parse(start), LocalDate.parse(end), pageable);

        List<ContractCostDTO> contractCostDTOS = new ArrayList<>();

        for (Contract contract : contracts) {
            double cost = 0.0;
            for (Project project : contract.getProjects()) {
                if (project.getEndDate().isAfter(LocalDate.parse(start)) && project.getEndDate().isBefore(LocalDate.parse(end)))
                for (Work work : project.getWorks()) {
                    if (work.getEndDate().isAfter(LocalDate.parse(start)) && work.getEndDate().isBefore(LocalDate.parse(end))) {
                        cost += work.getCost();
                    }
                }
            }
            ContractCostDTO contractCostDTO = dtoService.mapContractToCostDto(contract);
            contractCostDTO.setCost(cost);
            contractCostDTOS.add(contractCostDTO);
        }

        return new PageImpl<ContractCostDTO>(contractCostDTOS);

    }

    @GetMapping("/equipment/now")
    Page<EquipmentDTO> equipmentNow(Pageable pageable) {

        Page<Equipment> equipmentPage = equipmentRepository.findAll(pageable);

        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Equipment equipment : equipmentPage) {
            EquipmentDTO equipmentDTO = dtoService.mapEqipmentToDto(equipment);
            Work lastWork = new Work();
            lastWork.setEndDate(LocalDate.MIN);
            for (Work work : equipment.getWorks()) {
                if (work.getEndDate().isAfter(lastWork.getEndDate()) && work.getStartDate().isBefore(LocalDate.now())) {
                    lastWork = work;
                }
            }
            if (lastWork.getEndDate().isAfter(LocalDate.now())) equipmentDTO.setCurrentWork(lastWork.getDescription());
            else equipmentDTO.setCurrentWork("Оборудование свободно");
            equipmentDTOS.add(equipmentDTO);
        }

        return new PageImpl<>(equipmentDTOS);

    }

    @GetMapping("/equipment/at-date")
    Page<EquipmentDTO> equipmentAtTheDate(@RequestParam(name = "date") String date, Pageable pageable) {

        Page<Equipment> equipmentPage = equipmentRepository.findAll(pageable);

        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Equipment equipment : equipmentPage) {
            EquipmentDTO equipmentDTO = dtoService.mapEqipmentToDto(equipment);
            Work lastWork = new Work();
            lastWork.setEndDate(LocalDate.MIN);
            for (Work work : equipment.getWorks()) {
                System.out.println(work.getEndDate());
                System.out.println(lastWork.getEndDate()    );
                if (work.getEndDate().isAfter(lastWork.getEndDate()) && work.getStartDate().isBefore(LocalDate.parse(date))) {
                    lastWork = work;
                }
            }
            if (lastWork.getEndDate().isAfter(LocalDate.parse(date))) equipmentDTO.setCurrentWork(lastWork.getDescription());
            else equipmentDTO.setCurrentWork("Оборудование было свободно");
            equipmentDTOS.add(equipmentDTO);
        }

        return new PageImpl<>(equipmentDTOS);

    }

    @GetMapping("/projects/equipment-at-project")
    Page<EquipmentDTO> equipmentAtProject(@RequestParam(name = "project-id") Integer projectId, Pageable pageable) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Could not find project with that ID"));

        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Work work : project.getWorks()) {
            for (Equipment equipment : work.getEquipment()) {
                equipmentDTOS.add(dtoService.mapEqipmentToDto(equipment));
            }
        }

        return new PageImpl<>(equipmentDTOS);

    }

    @GetMapping("/contracts/equipment-at-contract")
    Page<EquipmentDTO> equipmentAtContract(@RequestParam(name = "contract-id") Integer contractId, Pageable pageable) {

        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new IllegalArgumentException("Could not find contract with that ID"));

        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Project project : contract.getProjects()) {
            for (Work work : project.getWorks()) {
                for (Equipment equipment : work.getEquipment()) {
                    equipmentDTOS.add(dtoService.mapEqipmentToDto(equipment));
                }
            }
        }

        return new PageImpl<>(equipmentDTOS);

    }

    @GetMapping("/projects/by-employee-between")
    Page<ProjectShortDTO> projectsByEmployee(@RequestParam(name = "employee-id") Integer employeeId,
                                             @RequestParam(name = "start") String start,
                                             @RequestParam(name = "end") String end,
                                             Pageable pageable) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Could not find employee with such ID"));

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        Collection<Set<Employee>> setCollection = Collections.singleton(employeeSet);

        List<ProjectShortDTO> list = new ArrayList<>();

        Page<Project> projects = projectRepository.findAllByEmployeesInAndStartDateBetweenOrStartDateBeforeAndEndDateAfter(setCollection, LocalDate.parse(start),
                LocalDate.parse(end), LocalDate.parse(start), LocalDate.parse(start), pageable);

        for (Project project : projects) {
            list.add(dtoService.mapProjectToShortDto(project));
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/contracts/by-employee-between")
    Page<ContractShortDTO> contractsByEmployee(@RequestParam(name = "employee-id") Integer employeeId,
                                             @RequestParam(name = "start") String start,
                                             @RequestParam(name = "end") String end,
                                             Pageable pageable) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Could not find employee with such ID"));

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        Collection<Set<Employee>> setCollection = Collections.singleton(employeeSet);

        List<ContractShortDTO> list = new ArrayList<>();

        Page<Contract> contracts = contractRepository.findAllByEmployeesInAndStartDateBetweenOrStartDateBeforeAndEndDateAfter(setCollection, LocalDate.parse(start),
                LocalDate.parse(end), LocalDate.parse(start), LocalDate.parse(start), pageable);

        for (Contract contract : contracts) {
            list.add(dtoService.mapContractToShortDto(contract));
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/works/subcontracted")
    Page<WorkDTO> subcontractedWorks(Pageable pageable) {

        Page<Work> works = workRepository.findAllBySubcontractorIsNotNull(pageable);

        List<WorkDTO> list = new ArrayList<>();

        for (Work work : works) {
            list.add(dtoService.mapWorkToDto(work));
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/projects/employees-statistics-by-id")
    ProjectEmployeesDTO projectEmployees(@RequestParam(name = "project-id") Integer projectId, Pageable pageable) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Could not find project with that ID"));

        ProjectEmployeesDTO dto = dtoService.mapProjectToEmployeesDto(project);
        dto.setAmountOfEmployees(0);
        Map<String, Integer> map = new HashMap<>();
        dto.setAmountOfEmployeesInCategories(map);

        for (Employee employee : project.getEmployees()) {

            String employeeJob = employee.getClass().getAnnotation(DiscriminatorValue.class).value();

            dto.setAmountOfEmployees(dto.getAmountOfEmployees() + 1);
            if (dto.getAmountOfEmployeesInCategories().containsKey(employeeJob)) {
                Integer amount = dto.getAmountOfEmployeesInCategories().get(employeeJob);
                dto.getAmountOfEmployeesInCategories().put(employeeJob, amount + 1);
            }
            else dto.getAmountOfEmployeesInCategories().put(employeeJob, 1);

        }

        return dto;

    }

    @GetMapping("/projects/employees-statistics-between")
    Page<ProjectEmployeesDTO> projectEmployees(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end, Pageable pageable) {

        Page<Project> projects = projectRepository.findAllByStartDateBetweenOrStartDateBeforeAndEndDateAfter(LocalDate.parse(start),
                LocalDate.parse(end), LocalDate.parse(start), LocalDate.parse(start), pageable);

        List<ProjectEmployeesDTO> list = new ArrayList<>();

        for (Project project : projects) {

            ProjectEmployeesDTO dto = dtoService.mapProjectToEmployeesDto(project);
            dto.setAmountOfEmployees(0);
            Map<String, Integer> map = new HashMap<>();
            dto.setAmountOfEmployeesInCategories(map);

            for (Employee employee : project.getEmployees()) {

                String employeeJob = employee.getClass().getAnnotation(DiscriminatorValue.class).value();

                dto.setAmountOfEmployees(dto.getAmountOfEmployees() + 1);
                if (dto.getAmountOfEmployeesInCategories().containsKey(employeeJob)) {
                    Integer amount = dto.getAmountOfEmployeesInCategories().get(employeeJob);
                    dto.getAmountOfEmployeesInCategories().put(employeeJob, amount + 1);
                } else dto.getAmountOfEmployeesInCategories().put(employeeJob, 1);

            }

            list.add(dto);
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/efficiency/equipment")
    Page<EfficiencyDTO> efficiencyEquipment(Pageable pageable) {

        List<EfficiencyDTO> list = new ArrayList<>();

        for (Equipment equipment : equipmentRepository.findAll(pageable)) {
            EfficiencyDTO efficiencyDTO = dtoService.mapEquipmentToEfficiencyDto(equipment);
            Double cost = 0.0;
            for (Work work : equipment.getWorks()) {
                cost += work.getCost();
            }
            efficiencyDTO.setEfficiency(cost);
            list.add(efficiencyDTO);
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/efficiency/projects")
    Page<EfficiencyDTO> efficiencyProjects(Pageable pageable) {

        List<EfficiencyDTO> list = new ArrayList<>();

        for (Project project : projectRepository.findAll(pageable)) {
            EfficiencyDTO efficiencyDTO = dtoService.mapProjectToEfficiencyDto(project);
            Double cost = 0.0;
            long time = project.getEndDate().toEpochDay() - project.getStartDate().toEpochDay();
            for (Work work : project.getWorks()) {
                cost += work.getCost();
            }
            efficiencyDTO.setEfficiency(cost/time);
            list.add(efficiencyDTO);
        }

        return new PageImpl<>(list);

    }

    @GetMapping("/efficiency/contracts")
    Page<EfficiencyDTO> efficiencyContratcts(Pageable pageable) {

        List<EfficiencyDTO> list = new ArrayList<>();

        for (Contract contract : contractRepository.findAll(pageable)) {

            EfficiencyDTO efficiencyDTO = dtoService.mapContractToEfficiencyDto(contract);
            Double cost = 0.0;
            long time = contract.getEndDate().toEpochDay() - contract.getStartDate().toEpochDay();

            for (Project project : contract.getProjects()) {

                for (Work work : project.getWorks()) {
                    cost += work.getCost();
                }

            }

            efficiencyDTO.setEfficiency(cost / time);
            list.add(efficiencyDTO);
        }

        return new PageImpl<>(list);

    }
}

