package ru.nsu.mrprince.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Page<Department> findAll(Pageable pageable);

}
