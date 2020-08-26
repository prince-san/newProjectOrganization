package ru.nsu.mrprince;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.mrprince.model.entities.*;
import ru.nsu.mrprince.model.entities.employees.Constructor;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.model.entities.employees.Engineer;
import ru.nsu.mrprince.model.entities.employees.Technician;
import ru.nsu.mrprince.repositories.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                                   ProjectRepository projectRepository, ContractRepository contractRepository,
                                   WorkRepository workRepository, EquipmentRepository equipmentRepository) {
        return args -> {
            Department department = new Department();
            department.setName("Отдел разработки");
            departmentRepository.save(department);
            Constructor constructor = new Constructor();
            constructor.setPatents(1);
            constructor.setName("Гешка");
            constructor.setBirthDate(LocalDate.of(1970, 1, 1));
            constructor.setDepartment(department);
            employeeRepository.save(constructor);
            Engineer engineer = new Engineer();
            engineer.setName("Витька");
            engineer.setBirthDate(LocalDate.of(1990, 1, 1));
            engineer.setDepartment(department);
            employeeRepository.save(engineer);
            department.setLeader(constructor);
            departmentRepository.save(department);
            Department department1 = new Department();
            department1.setName("Отдел поддержки");
            departmentRepository.save(department1);
            Technician technician = new Technician();
            technician.setName("Колька");
            technician.setBirthDate(LocalDate.of(1975, 1, 1));
            technician.setDepartment(department1);
            employeeRepository.save(technician);
            department1.setLeader(technician);
            departmentRepository.save(department1);

            Project project = new Project();
            project.setName("Постройка моста");
            project.setSupervisor(constructor);
            project.setStartDate(LocalDate.of(2020, 4, 1));
            project.setEndDate(LocalDate.of(2020, 10, 1));
            projectRepository.save(project);

            Contract contract = new Contract();
            contract.setName("Строительные работы в городе Новосибирске");
            contract.setSupervisor(engineer);
            contract.setStartDate(LocalDate.of(2020, 4, 1));
            contract.setEndDate(LocalDate.of(2020, 10, 1));

            Set<Project> projects = new HashSet<>();
            projects.add(project);
            contract.setProjects(projects);
            contractRepository.save(contract);

            Set<Contract> contracts = new HashSet<>();
            contracts.add(contract);
            project.setContracts(contracts);
            projectRepository.save(project);

            Work work1 = new Work();
            work1.setCost(1000.0);
            work1.setDescription("Вывоз грунта");
            work1.setStartDate(LocalDate.of(2020, 4, 1));
            work1.setEndDate(LocalDate.of(2020, 6, 1));
            workRepository.save(work1);

            Work work2 = new Work();
            work2.setCost(1500.0);
            work2.setDescription("Ремонт дороги");
            work2.setStartDate(LocalDate.of(2020, 8, 1));
            work2.setEndDate(LocalDate.of(2020, 9, 1));
            workRepository.save(work2);


            Set<Work> works = new HashSet<>();
            works.add(work1);
            works.add(work2);
            project.setWorks(works);
            projectRepository.save(project);

            Equipment equipment = new Equipment();
            equipment.setDepartment(department);
            equipment.setName("Самосвал");
            equipment.setTechnician(technician);
            equipmentRepository.save(equipment);

            Set<Work> workSet = new HashSet<>();
            workSet.add(work1);
            equipment.setWorks(workSet);
            equipmentRepository.save(equipment);

            Equipment equipment1 = new Equipment();
            equipment1.setDepartment(department1);
            equipment1.setName("Бульдозер");
            equipment1.setTechnician(technician);
            equipmentRepository.save(equipment1);

            Set<Work> workSet1 = new HashSet<>();
            workSet1.add(work2);
            equipment1.setWorks(workSet1);
            equipmentRepository.save(equipment1);

            Set<Equipment> equipmentSet = new HashSet<>();
            equipmentSet.add(equipment);
            work1.setEquipment(equipmentSet);
            workRepository.save(work1);

            Set<Equipment> equipmentSet1 = new HashSet<>();
            equipmentSet1.add(equipment1);
            work2.setEquipment(equipmentSet1);
            workRepository.save(work2);

            Set<Employee> employees = new HashSet<>();
            employees.add(engineer);
            employees.add(technician);
            employees.add(constructor);
            project.setEmployees(employees);
            projectRepository.save(project);

        };
    }

}
