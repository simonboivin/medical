package com.atos.medical.model.services;

import com.atos.medical.model.entities.CitiesEntity;
import com.atos.medical.model.repositories.CitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {

    public final CitiesRepository citiesRepository;

    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

public List<CitiesEntity> getAllCities(){
        return (List<CitiesEntity>) citiesRepository.findAll();
}

public Optional<CitiesEntity> getCityById(int id){
        return citiesRepository.findById(id);
}

}
