package com.atos.medical.model.services;

import com.atos.medical.model.entities.CitiesEntity;
import com.atos.medical.model.entities.PatientsEntity;
import com.atos.medical.model.repositories.PatientsRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsService {

    public final PatientsRepository patientsRepository;
    public final CitiesService citiesService;

    public PatientsService(PatientsRepository patientsRepository, CitiesService citiesService) {
        this.patientsRepository = patientsRepository;
        this.citiesService = citiesService;
    }

    /**
     * Return the list of all patients
     */
    public List<PatientsEntity> getAllPatients() {
        return ((List<PatientsEntity>) patientsRepository.findAll());
    }

    /**
     * Return a patient find by its ID
     */
    public Optional<PatientsEntity> getPatientById(int id) {
        return patientsRepository.findById(id);
    }

    /**
     * Set all parameter of a patient
     */
    private PatientsEntity setPatient(PatientsEntity patient, String firstName, String lastName, String email, String phoneNumber, String photo, CitiesEntity city) {
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhoneNumber(phoneNumber);
        patient.setPhoto(photo);
        patient.setCity(city);
        return patient;
    }


    /**
     * Add a patient in the database
     */
    @Transactional
    public PatientsEntity addPatient(String firstName, String lastName, String email, String phoneNumber, String photo, int idCity) {
        PatientsEntity patient = new PatientsEntity();
        Optional<CitiesEntity> optionalCity = citiesService.getCityById(idCity);
        if (optionalCity.isPresent()) {
            setPatient(patient, firstName, lastName, email, phoneNumber, photo, optionalCity.get());
            patientsRepository.save(patient);
            return patient;
        } else {
            throw new ObjectNotFoundException(idCity, "City not found");
        }
    }

    /**
     * Edit a patient
     */
    @Transactional
    public PatientsEntity updatePatientById(int id, String firstName, String lastName, String email, String phoneNumber, String photo, int idCity) {
        Optional<PatientsEntity> patientOptional = getPatientById(id);
        if (patientOptional.isPresent()) {
            return patientsRepository.save(
                    setPatient(patientOptional.get(), firstName, lastName, email, phoneNumber, photo, citiesService.getCityById(idCity).orElseThrow(
                            () -> new ObjectNotFoundException(idCity, "City not found")
                    )));
        } else {
            throw new ObjectNotFoundException(id, "Patient not found");
        }
    }

    /**
     * Delete the patient
     *
     * @param id Id of the patient to delete
     */
    @Transactional
    public void deletePatientById(int id) {
        Optional<PatientsEntity> patientOptional = getPatientById(id);
        if (patientOptional.isPresent()) {
            patientsRepository.delete(patientOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "Patient not found");
        }
    }

}
