package com.microservicio.plandeentrenamiento.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicio.plandeentrenamiento.models.entity.EquipoPlan;

public interface EquipoPlanRepository extends CrudRepository<EquipoPlan, Long> {

}
