package com.atos.medical.controller.api;


import com.atos.medical.model.entities.PatientsEntity;
import com.atos.medical.model.services.PatientsService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientsControllerApi {

    private final PatientsService patientsService;

    public PatientsControllerApi(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    /**
     * Get list of all patients
     */
    @GetMapping(path = "", produces = "application/json")
    public List<PatientsEntity> getPatientsListApi() {
        return patientsService.getAllPatients();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public PatientsEntity getPatientByIdApi(@PathVariable("id") int id) {
        Optional<PatientsEntity> patientOptional = patientsService.getPatientById(id);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient is not found");
        }
    }

    /**
     * Add a new patient
     */
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<PatientsEntity> addPatientApi(@RequestBody PatientsEntity patientInput) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    patientsService.addPatient(patientInput.getFirstName(),
                            patientInput.getLastName(),
                            patientInput.getEmail(),
                            patientInput.getPhoneNumber(),
                            patientInput.getPhoto(),
                            patientInput.getCity().getId()));
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " City " + patientInput.getCity().getId() + " not found");
        }
    }

    /**
     * Edit a patient
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<PatientsEntity> editPatientApi(@PathVariable("id") int id, @RequestBody PatientsEntity patientInput) {
        Optional<PatientsEntity> patientOptional = patientsService.getPatientById(id);
        if (patientOptional.isPresent()) {
            try {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                        patientsService.updatePatientById(id,
                                patientInput.getFirstName(),
                                patientInput.getLastName(),
                                patientInput.getEmail(),
                                patientInput.getPhoneNumber(),
                                patientInput.getPhoto(),
                                patientInput.getCity().getId()));
            } catch (ObjectNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " City " + patientInput.getCity().getId() + " not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient is not found");
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> deletePatientApi(@PathVariable("id") int id) {
        Optional<PatientsEntity> patientOptional = patientsService.getPatientById(id);
        if (patientOptional.isPresent()) {
            patientsService.deletePatientById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Patient " + id + " deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient is not found");
        }
    }

}
