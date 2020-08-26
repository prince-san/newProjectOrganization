package ru.nsu.mrprince.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Contract;
import ru.nsu.mrprince.model.entities.Project;
import ru.nsu.mrprince.model.entities.employees.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Page<Contract> findAll(Pageable pageable);

    Page<Contract> findAllByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Contract> findAllByStartDateBetweenOrStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate startDate2, LocalDate startDate3, LocalDate endDate, Pageable pageable);

    Page<Contract> findAllByEndDateBetween(LocalDate endDate, LocalDate endDate2, Pageable pageable);

    Page<Contract> findAllByEmployeesInAndStartDateBetweenOrStartDateBeforeAndEndDateAfter(Collection<Set<Employee>> employees, LocalDate startDate, LocalDate startDate2, LocalDate startDate3, LocalDate endDate, Pageable pageable);

}
