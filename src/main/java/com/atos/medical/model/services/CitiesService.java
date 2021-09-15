package com.atos.medical.model.services;

import com.atos.medical.model.entities.CitiesEntity;
import com.atos.medical.model.repositories.CitiesRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {

    public final CitiesRepository citiesRepository;

    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<CitiesEntity> getAllCities() {
        return (List<CitiesEntity>) citiesRepository.findAll();
    }

    public Optional<CitiesEntity> getCityById(int id) {
        return citiesRepository.findById(id);
    }

    private CitiesEntity setCity(CitiesEntity city, String name, String zipCode) {
        city.setName(name);
        city.setZipCode(zipCode);
        return city;
    }


    @Transactional
    public CitiesEntity addCity(String name, String zipCode) {
        CitiesEntity city = new CitiesEntity();
        return citiesRepository.save(setCity(city, name, zipCode));
    }

    @Transactional
    public CitiesEntity updateCityById(int id, String name, String zipCode) {
        Optional<CitiesEntity> cityOptional = getCityById(id);
        if (cityOptional.isPresent()) {
            return citiesRepository.save(setCity(cityOptional.get(), name, zipCode));
        } else {
            throw new ObjectNotFoundException(id, "City not found");
        }
    }

    @Transactional
    public void deleteCity(int id){
        Optional<CitiesEntity> cityOptional = getCityById(id);
        if (cityOptional.isPresent()) {
            citiesRepository.delete(cityOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "City not found");
        }
    }


}
