package ru.nsu.mrprince.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Project;
import ru.nsu.mrprince.model.entities.employees.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Page<Project> findAll(Pageable pageable);

    Page<Project> findAllByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Project> findAllByStartDateBetweenOrStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate startDate2,
                                                                            LocalDate startDate3, LocalDate endDate,
                                                                            Pageable pageable);

    Page<Project> findAllByEndDateBetween(LocalDate endDate, LocalDate endDate2, Pageable pageable);

    Page<Project> findAllByEmployeesInAndStartDateBetweenOrStartDateBeforeAndEndDateAfter(Collection<Set<Employee>> employees,
                                                                                          LocalDate startDate, LocalDate startDate2,
                                                                                          LocalDate startDate3, LocalDate endDate,
                                                                                          Pageable pageable);

}
