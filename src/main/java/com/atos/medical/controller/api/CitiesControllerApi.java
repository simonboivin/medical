package com.atos.medical.controller.api;

import com.atos.medical.model.entities.CitiesEntity;
import com.atos.medical.model.services.CitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CitiesControllerApi {

    private final CitiesService citiesService;

    public CitiesControllerApi(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<CitiesEntity> getCitiesListApi() {
        return citiesService.getAllCities();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public CitiesEntity getCityByIdApi(@PathVariable("id") int id) {
        Optional<CitiesEntity> cityOptional = citiesService.getCityById(id);
        if (cityOptional.isPresent()) {
            return cityOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

    /**
     * Add a new city
     */
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<CitiesEntity> addCityApi(@RequestBody CitiesEntity cityInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                citiesService.addCity(
                        cityInput.getName(),
                        cityInput.getZipCode()
                ));
    }

    /**
     * Edit a patient
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<CitiesEntity> editCityApi(@PathVariable("id") int id, @RequestBody CitiesEntity cityInput) {
        Optional<CitiesEntity> cityOptional = citiesService.getCityById(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    citiesService.updateCityById(id,
                            cityInput.getName(),
                            cityInput.getZipCode()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteCityApi(@PathVariable("id") int id) {
        Optional<CitiesEntity> cityOptional = citiesService.getCityById(id);
        if (cityOptional.isPresent()) {
            citiesService.deleteCity(id);
            return ResponseEntity.status(HttpStatus.OK).body("City " + id + " deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

}
