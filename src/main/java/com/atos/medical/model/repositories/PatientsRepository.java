package com.atos.medical.model.repositories;

import com.atos.medical.model.entities.PatientsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientsRepository extends CrudRepository<PatientsEntity, Integer> {
}
