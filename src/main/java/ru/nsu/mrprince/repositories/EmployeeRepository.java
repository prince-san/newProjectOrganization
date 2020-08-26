package ru.nsu.mrprince.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Department;
import ru.nsu.mrprince.model.entities.employees.Employee;

import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByDepartment(Department department, Pageable pageable);

    Page<Employee> findAllByBirthDateBetween(LocalDate start, LocalDate finish, Pageable pageable);

    Page<Employee> findAllByDepartmentAndBirthDateBetween(Department department, LocalDate start, LocalDate finish, Pageable pageable);

}
