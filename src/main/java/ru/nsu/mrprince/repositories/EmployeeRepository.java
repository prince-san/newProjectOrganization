package ru.nsu.mrprince.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.employees.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
