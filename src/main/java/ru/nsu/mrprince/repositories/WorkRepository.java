package ru.nsu.mrprince.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Work;

public interface WorkRepository extends JpaRepository<Work, Integer> {

    Page<Work> findAllBySubcontractorIsNotNull(Pageable pageable);

}
