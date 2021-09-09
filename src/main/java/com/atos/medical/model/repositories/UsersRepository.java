package com.atos.medical.model.repositories;

import com.atos.medical.model.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {
}
