package ru.nsu.mrprince.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.mrprince.model.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Page<Equipment> findAll(Pageable pageable);

}
